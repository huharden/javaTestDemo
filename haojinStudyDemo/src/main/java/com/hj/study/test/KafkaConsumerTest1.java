package com.hj.study.test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;


/**
 *
 * Title: KafkaConsumerTest
 * Description:
 *  kafka消费者 demo
 * Version:1.0.0
 * @author pancm
 * @date 2018年1月26日
 */
@Slf4j
public class KafkaConsumerTest1 implements Runnable {

    private final KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String, String> msgList;
    private final String topic;
    private static final String GROUPID = "groupA";

    public KafkaConsumerTest1(String topicName) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.45.47.69:9095");
        props.put("group.id", GROUPID);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<String, String>(props);
        this.topic = topicName;
        this.consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        int messageNo = 1;
        log.info("---------开始消费---------");
        try {
            msgList = consumer.poll(Duration.ofMillis(10000));
            if (null != msgList && msgList.count() > 0) {
                for (ConsumerRecord<String, String> record : msgList) {
                    log.info(messageNo + "=======receive: key = " + record.key() + ", value = " + record.value() + " offset===" + record.offset());
                }

            }
            else {
                Thread.sleep(1000);
            }

//            for (;;) {
//                msgList = consumer.poll(Duration.ofMillis(10000));
//                //msgList = consumer.poll(1000);
//                if (null != msgList && msgList.count() > 0) {
//                    for (ConsumerRecord<String, String> record : msgList) {
//                        //消费100条就打印 ,但打印的数据不一定是这个规律的
//                        if (messageNo % 100 == 0) {
//
//                            log.info(messageNo + "=======receive: key = " + record.key() + ", value = " + record.value() + " offset===" + record.offset());
//                        }
//                        //当消费了1000条就退出
//                        if (messageNo % 1000 == 0) {
//                            break;
//                        }
//                        messageNo++;
//                    }
//                }
//                else {
//                    Thread.sleep(1000);
//                }
//            }
        }
        catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        finally {
            consumer.close();
        }
    }

    public static void main(String[] args) {
        KafkaConsumerTest1 test1 = new KafkaConsumerTest1("KAFKA_TEST");
        Thread thread1 = new Thread(test1);
        thread1.start();

//        Duration duration10 = Duration.ofSeconds(100);
//        Duration duration11 = duration10.minusDays(1);
//        log.info("aaaaaaaaaaaaaa1: " + duration10);
//        log.info("aaaaaaaaaaaaaa2: " + duration11);
//        int a = 1 << 30;
//
//        System.out.println(a);
//
//        int b = 1073741824 >>> 1;
//        System.out.println(b);

//        System.out.println("a1: " + (5 >>> 1)); //2
//        System.out.println("a2: " + (5 | 2)); // 7
//
//        System.out.println("a1: " + (7 >>> 2)); //1
//        System.out.println("a2: " + (7 | 1)); // 7
//
//        System.out.println("a1: " + (7 >>> 4)); //0
//        System.out.println("a2: " + (7 | 0)); // 7
//
//        System.out.println("a1: " + (7 >>> 8)); //0


//        n |= n >>> 1;
//
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;



    }
}
