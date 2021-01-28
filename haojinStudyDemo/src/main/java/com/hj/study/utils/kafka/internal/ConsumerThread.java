package com.hj.study.utils.kafka.internal;


import com.hj.study.config.KafkaConfiguration;
import com.hj.study.utils.DateUtil;
import com.hj.study.utils.SpringContextUtils;
import com.hj.study.utils.kafka.IBusinessConsumeService;
import com.hj.study.utils.kafka.thread.DefaultDaemonThreadFactory;
import com.hj.study.utils.kafka.thread.SafeInterruptThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.InterruptException;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/**
 * 封装消费逻辑，实现异步提交线程池处理，如果线程池处理速度慢，自动暂停消费者来调节消费速度。
 * 目的是为了避免常见的消费者处理过慢引起过多的rebalance操作
 */
@Slf4j
@DependsOn("springContextUtils")
public class ConsumerThread extends SafeInterruptThread {

    @Autowired
    private static SpringContextUtils springContextUtils;

    private static final ExecutorService DEFAULT_POOL; // 线程池

    static {
        KafkaConfiguration kafkaConfiguration = springContextUtils.getBean("kafkaConfiguration", KafkaConfiguration.class);
        // 初始化线程池，这里必须用AbortPolicy策略，用于后续可以知道是否提交线程池失败，以便调节消费速度
        DEFAULT_POOL = new ThreadPoolExecutor(kafkaConfiguration.getCorePoolSize(), kafkaConfiguration.getMaximumPoolSize(),
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(kafkaConfiguration.getQueueCapacity()), new DefaultDaemonThreadFactory("defaultKafkaConsumerPool"),
                new ThreadPoolExecutor.AbortPolicy());
    }

    private Consumer<String, String> consumer;

    private Map<String, Object> consumerConfigs;

    private ExecutorService pool;

    private IBusinessConsumeService businessConsumeService;

    private Iterator<ConsumerRecord<String, String>> recordIterator;

    //private ConsumerRecord<String, String> currentRecord;

    // 批量消费
    private List<ConsumerRecord<String, String>> recordList;

    private Object topics;

    /**
     * 记录当前消费者获取分配的所有分区
     */
    private Collection<TopicPartition> assignedPartitions = new HashSet<>();

    /**
     * 由于业务线程处理速度过慢，调用pause暂停所有分区的消费并且设置paused为true
     */
    private boolean paused = false;

    /**
     * 由于业务原因需要pause和resume的分区，提交到对应集合中，由每次poll之前再执行集合的数据
     */
    private Collection<TopicPartition> waitForPausePartitions = new HashSet<>();
    private Collection<TopicPartition> waitForResumePartitions = new HashSet<>();

    private Lock waitForPauseAndResumeLock = new ReentrantLock();

    private boolean useDefaultPool;

    /**
     * 记录由于业务原因暂停的分区，当业务处理速度过慢时需要保存已暂停的分区，
     * 再进行所有分区全部暂停，等恢复所有分区时再暂停之前保存已暂停的分区
     */
    private Collection<TopicPartition> businessPausePartitions = new HashSet<>();

    public ConsumerThread(String id, Object topics, IBusinessConsumeService businessConsumeService,
                          Map<String, Object> consumerConfigs, boolean useDefaultPool) {
        super("ConsumerThread_" + id);
        this.consumerConfigs = consumerConfigs;
        this.pool = pool;
        this.businessConsumeService = businessConsumeService;
        this.topics = topics;
        this.useDefaultPool = useDefaultPool;
        if (this.useDefaultPool) {
            this.pool = DEFAULT_POOL;
        }
        else {
            KafkaConfiguration kafkaConfiguration = springContextUtils.getBean("kafkaConfiguration", KafkaConfiguration.class);
            // 初始化线程池，这里必须用AbortPolicy策略，用于后续可以知道是否提交线程池失败，以便调节消费速度
            this.pool = new ThreadPoolExecutor(kafkaConfiguration.getCorePoolSize(), kafkaConfiguration.getMaximumPoolSize(),
                    60L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(kafkaConfiguration.getQueueCapacity()), new DefaultDaemonThreadFactory(id + "_kafkaConsumerPool"),
                    new ThreadPoolExecutor.AbortPolicy());
        }
    }

    @Override
    public void run() {
        consumer = createNewConsumer(consumerConfigs);
        //两次poll间隔最长时间，超出这个时间，将会导致消费者退出，进而引发rebalance操作，目前通过严格控制一旦超过2/3的maxPollIntervalMs时间就执行下一次poll操作
        int maxPollIntervalMs = MapUtils.getIntValue(consumerConfigs, ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 300000);
        while (true) {
            long startTimeMillis = DateUtil.getCurrentTimeMillis();
            //消费未暂停时检查所有分区，对由于业务原因需要暂停的分区进行暂停消费操作
            if (!paused) {
                businessPauseAndResumeTopicPartitions();
            }
            ConsumerRecords<String, String> consumerRecords = safelyPoll(consumer);

            if (recordIterator == null || !recordIterator.hasNext()) {
                recordIterator = consumerRecords.iterator();
            }
            while (CollectionUtils.isNotEmpty(recordList) || recordIterator.hasNext()) {
                if(CollectionUtils.isEmpty(recordList)){
                    recordList = new ArrayList<>();
                    while(recordIterator.hasNext()){
                        recordList.add(recordIterator.next());
                    }
                }
                //循环尝试提交给线程池处理，直到成功或者超过maxPollIntervalMs的2/3时间
                boolean executeNewRecordResult = false;
                while (true) {
                    executeNewRecordResult = executeNewRecord(recordList);
                    if (!executeNewRecordResult && DateUtil.getCurrentTimeMillis() - startTimeMillis < maxPollIntervalMs / 3 * 2) {
                        try {
                            Thread.currentThread().sleep(10);
                        }
                        catch (InterruptedException e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                    else {
                        break;
                    }
                }
                //超过maxPollIntervalMs的2/3时间，线程池还没有资源处理新数据，则跳出循环，避免处理时间过长
                if (!executeNewRecordResult) {
                    //如果之前没pause所有分区，则备份当前暂停的分区，进行pause所有分区操作
                    if (!paused) {
                        log.debug("pause on partitions:{}, maxPollIntervalMs:{}, cost:{}", assignedPartitions, maxPollIntervalMs, DateUtil.getCurrentTimeMillis() - startTimeMillis);
                        businessPausePartitions = consumer.paused();
                        safelyPauseTopicPartition(consumer, assignedPartitions);
                        paused = true;
                    }
                    break;
                }
                recordList = null;
            }

            //当前poll的所有数据都成功提交线程池，resume所有消费分区
            if (/*currentRecord == null*/CollectionUtils.isEmpty(recordList) && !recordIterator.hasNext()) {
                //如果是由于业务处理速度慢导致的消费暂停，则恢复所有消费的分区
                if (paused) {
                    safelyResumeTopicPartition(consumer, assignedPartitions);
                    paused = false;
                }

                //判断线程是否被中断
                if (isStop()) {
                    break;
                }
            }
        }

        //关闭消费者
        safelyCloseConsumer(consumer);
    }

    private boolean executeNewRecord(List<ConsumerRecord<String, String>> consumerList) {
        try {
            PoolConsumerRunnable poolConsumerRunnable = new PoolConsumerRunnable(consumerList, businessConsumeService);
            pool.execute(poolConsumerRunnable);
        }
        catch (RejectedExecutionException e) {
            return false;
        }
        return true;
    }

    private void safelyResumeTopicPartition(Consumer consumer, Collection<TopicPartition> topicPartitions) {
        for (TopicPartition topicPartition : topicPartitions) {
            try {
                consumer.resume(Collections.singleton(topicPartition));
            }
            catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        log.debug("resume topicPartition:{}", topicPartitions);
    }

    private void safelyPauseTopicPartition(Consumer consumer, Collection<TopicPartition> topicPartitions) {
        for (TopicPartition topicPartition : topicPartitions) {
            try {
                consumer.pause(Collections.singleton(topicPartition));
            }
            catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        log.debug("pause topicPartition:{}", topicPartitions);
    }

    private ConsumerRecords<String, String> safelyPoll(Consumer<String, String> consumer) {
        try {
            return consumer.poll(Duration.ofSeconds(1));
        }
        catch (InterruptException e) {
            //线程被结束，直接返回空，待之前已消费数据处理完再结束线程
            return ConsumerRecords.empty();
        }
        catch (Exception e) {
            //其他无法预料异常，结束consumer,重新订阅
            safelyCloseConsumer(consumer);
            this.consumer = createNewConsumer(consumerConfigs);
            return ConsumerRecords.empty();
        }
    }

    private Consumer<String, String> createNewConsumer(Map<String, Object> consumerConfigs) {
        Consumer<String, String> consumer = new KafkaConsumer<String, String>(consumerConfigs);
        if (topics instanceof Collection) {
            consumer.subscribe((Collection<String>) topics, new InternalConsumerRebalanceListener(this));
        }
        else if (topics instanceof Pattern) {
            consumer.subscribe((Pattern) topics, new InternalConsumerRebalanceListener(this));
        }
        else if (topics instanceof String) {
            String topic = (String) topics;
            consumer.subscribe(Collections.singleton(topic), new InternalConsumerRebalanceListener(this));
        }
        return consumer;
    }

    private void safelyCloseConsumer(Consumer<String, String> consumer) {
        try {
            consumer.close();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void businessPauseAndResumeTopicPartitions() {
        if (!businessPausePartitions.isEmpty()) {
            safelyPauseTopicPartition(consumer, businessPausePartitions);
            businessPausePartitions = new HashSet<>();
        }
        waitForPauseAndResumeLock.lock();
        try {
            if (!waitForPausePartitions.isEmpty()) {
                safelyPauseTopicPartition(consumer, waitForPausePartitions);
                waitForPausePartitions = new HashSet<>();
            }
            if (!waitForResumePartitions.isEmpty()) {
                safelyResumeTopicPartition(consumer, waitForResumePartitions);
                waitForResumePartitions = new HashSet<>();
            }
        }
        finally {
            waitForPauseAndResumeLock.unlock();
        }
    }

    public void updateWaitForPauseAndResumeTopicPartitions(Collection<TopicPartition> waitForPausePartitions, Collection<TopicPartition> waitForResumePartitions) {
        waitForPauseAndResumeLock.lock();
        try {
            this.waitForPausePartitions = waitForPausePartitions;
            this.waitForResumePartitions = waitForResumePartitions;
        }
        finally {
            waitForPauseAndResumeLock.unlock();
        }
    }

    public Collection<TopicPartition> getAssignedPartitions() {
        return Collections.unmodifiableCollection(assignedPartitions);
    }

    /**
     * 保存分配到的分区，用于执行消费者的pause和resume操作
     */
    private final class InternalConsumerRebalanceListener implements ConsumerRebalanceListener {

        ConsumerThread consumerThread;

        private InternalConsumerRebalanceListener(ConsumerThread consumerThread) {
            this.consumerThread = consumerThread;
        }

        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            log.debug("onPartitionsRevoked:{}", partitions);
        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            log.debug("onPartitionsAssigned:{}", partitions);
            //保存最新分配的分区数据
            consumerThread.assignedPartitions = partitions;
            //清空之前业务需要暂停或恢复的分区数据
            waitForPauseAndResumeLock.lock();
            try {
                consumerThread.waitForPausePartitions = new HashSet<>();
                consumerThread.waitForResumePartitions = new HashSet<>();
            }
            finally {
                waitForPauseAndResumeLock.unlock();
            }
            consumerThread.businessPausePartitions = new HashSet<>();
        }
    }
}

