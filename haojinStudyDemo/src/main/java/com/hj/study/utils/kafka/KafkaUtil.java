package com.hj.study.utils.kafka;


import com.hj.study.config.KafkaConfiguration;
import com.hj.study.utils.SpringContextUtils;
import com.hj.study.utils.kafka.internal.ConsumerThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Slf4j
@DependsOn("springContextUtils")
public class KafkaUtil {

    @Autowired
    private static SpringContextUtils springContextUtils;

    private static Set<String> existTopics = new HashSet<>();

    private static KafkaTemplate<String, String> kafkaTemplate;

    private static synchronized KafkaTemplate<String, String> getKafkaTemplate() {
        if (kafkaTemplate == null) {
            kafkaTemplate = springContextUtils.getBean("kafkaTemplate", KafkaTemplate.class);
        }
        return kafkaTemplate;
    }

    public static void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
        KafkaUtil.kafkaTemplate = kafkaTemplate;
    }

    private static AdminClient adminClient;

    private static synchronized AdminClient getAdminClient() {
        if (adminClient == null) {
            adminClient = springContextUtils.getBean("adminClient", AdminClient.class);
        }
        return adminClient;
    }

    public static ListenableFuture<SendResult<String, String>> sendMessage(String topic, String value) {
        tryCreateTopic(topic);
        ListenableFuture<SendResult<String, String>> future = getKafkaTemplate().send(topic, value);
        return future;
    }

    public static ListenableFuture<SendResult<String, String>> sendMessage(String topic, String key, String value) {
        tryCreateTopic(topic);
        ListenableFuture<SendResult<String, String>> future = getKafkaTemplate().send(topic, key, value);
        return future;
    }

    private static Map<String, ConsumerThread> consumerMap = new ConcurrentHashMap();

    /**
     * 创建新的consumer，并启动新的线程去消费对应的consumer，消费的结果提交到默认的线程池处理
     *
     * @param id                     consumer对应的唯一标识
     * @param topics                 消费的主题，接收类型可以为{@link String}，{@link Collection}或者{@link Pattern}
     * @param businessConsumeService 注册业务处理函数，用于处理消费的每条数据
     */
    public static synchronized void startConsumer(String id, Object topics, IBusinessConsumeService businessConsumeService) {
        startConsumer(id, topics, businessConsumeService, true);
    }

    /**
     * 创建新的consumer，并启动新的线程去消费对应的consumer，消费的结果提交到默认的线程池处理
     *
     * @param id                     consumer对应的唯一标识
     * @param topics                 消费的主题，接收类型可以为{@link String}，{@link Collection}或者{@link Pattern}
     * @param businessConsumeService 注册业务处理函数，用于处理消费的每条数据
     */
    public static synchronized void startConsumer(String id, Object topics, IBusinessConsumeService businessConsumeService, boolean useDefaultPool) {
        KafkaConfiguration kafkaConfiguration = springContextUtils.getBean("kafkaConfiguration", KafkaConfiguration.class);
        //KafkaConfiguration kafkaConfiguration = SpringContext.getBean(KafkaConfiguration.class);
        startConsumer(id, topics, businessConsumeService, kafkaConfiguration.consumerConfigs(), useDefaultPool);
    }

    /**
     * 创建新的consumer，并启动新的线程去消费对应的consumer，消费的结果提交到默认的线程池处理
     *
     * @param id                     consumer对应的唯一标识
     * @param topics                 消费的主题，接收类型可以为{@link String}，{@link Collection}或者{@link Pattern}
     * @param businessConsumeService 注册业务处理函数，用于处理消费的每条数据
     */
    public static synchronized void startConsumer(String id, Object topics, IBusinessConsumeService businessConsumeService, Map<String, Object> consumerConfigs) {
        startConsumer(id, topics, businessConsumeService, consumerConfigs, true);
    }

    /**
     * 创建新的consumer，并启动新的线程去消费对应的consumer，消费的结果提交到默认的线程池处理
     *
     * @param id                     consumer对应的唯一标识
     * @param topics                 消费的主题，接收类型可以为{@link String}，{@link Collection}或者{@link Pattern}
     * @param businessConsumeService 注册业务处理函数，用于处理消费的每条数据
     * @param consumerConfigs        自定义消费者配置参数
     */
    public static synchronized void startConsumer(String id, Object topics, IBusinessConsumeService businessConsumeService,
                                                  Map<String, Object> consumerConfigs, boolean useDefaultPool) {
        internalStartConsumer(id, topics, businessConsumeService, consumerConfigs, useDefaultPool);
    }

    /**
     * 创建新的consumer，并启动新的线程去消费对应的consumer，消费的结果提交到默认的线程池处理
     *
     * @param id                     consumer对应的唯一标识
     * @param topics                 消费的主题，接收类型可以为{@link String}，{@link Collection}或者{@link Pattern}
     * @param businessConsumeService 注册业务处理函数，用于处理消费的每条数据
     * @param consumerConfigs        自定义消费者配置参数
     */
    public static synchronized void internalStartConsumer(String id, Object topics, IBusinessConsumeService businessConsumeService,
                                                          Map<String, Object> consumerConfigs, boolean useDefaultPool) {
        ConsumerThread consumerThread = consumerMap.get(id);
        if (consumerThread != null) {
            consumerThread.stopThread();
        }
        if (!(topics instanceof Collection) && !(topics instanceof Pattern) && !(topics instanceof String)) {
            throw new RuntimeException("输入的topics不符合规则");
        }

        //如果不存在topic，尝试创建topic
        if (topics instanceof String) {
            tryCreateTopic((String) topics);
        }
        else if (topics instanceof Collection) {
            Collection<String> topicCollections = (Collection<String>) topics;
            for (String topic : topicCollections) {
                tryCreateTopic(topic);
            }
        }

        //新增消费者线程
        consumerThread = new ConsumerThread(id, topics, businessConsumeService, consumerConfigs, useDefaultPool);
        consumerMap.put(id, consumerThread);
        consumerThread.start();
    }

    private static void tryCreateTopic(String topic) {
        if (!existTopics.contains(topic)) {
            //todo
        }
    }

    /**
     * 根据消费者id结束对应的消费者线程
     */
    public static synchronized void stopConsumer(String id) {
        ConsumerThread consumerThread = consumerMap.get(id);
        if (consumerThread != null) {
            consumerThread.stopThread();
        }
        consumerMap.remove(id);
    }

    /**
     * 异步提交需要暂停和恢复的分区，针对延时消费场景
     */
    public static synchronized void updateWaitForPauseAndResumeTopicPartitions(String id, Collection<TopicPartition> waitForPausePartitions, Collection<TopicPartition> waitForResumePartitions) {
        ConsumerThread consumerThread = consumerMap.get(id);
        if (consumerThread != null) {
            consumerThread.updateWaitForPauseAndResumeTopicPartitions(waitForPausePartitions, waitForResumePartitions);
        }
    }

    /**
     * 根据消费者id获取消费者已分配的分区
     */
    public static synchronized Collection<TopicPartition> getAssignedPartitions(String id) {
        ConsumerThread consumerThread = consumerMap.get(id);
        if (consumerThread != null) {
            return consumerThread.getAssignedPartitions();
        }
        return Collections.emptySet();
    }

    public static void main(String[] args) {
        //发送消息样例
        for (int i = 0; i < 10; i++) {
            KafkaUtil.sendMessage("testTopic3", "test" + i);
        }
        //启动消费者样例
        KafkaUtil.startConsumer("testTopic3", "testTopic3", new IBusinessConsumeService() {

            @Override
            public void consumeList(List<ConsumerRecord<String, String>> records) {
                log.info("=============== Total " + records.size() + " events in this batch ..");

                List<String> list = new ArrayList<String>();
                for (ConsumerRecord<String, String> record : records) {
                    Optional<?> kafkaMessage = Optional.ofNullable(record.value());
                    if (kafkaMessage.isPresent()) {
                        String message = record.value();
                        list.add(message);
                        String topic = record.topic();
                        log.info("message = {}",  message);
                    }
                }
                this.batchHandle(list);

            }

            private void batchHandle(List<String> list) {
                log.info("执行批量插入逻辑:{}", list);
            }
        });
    }
}

