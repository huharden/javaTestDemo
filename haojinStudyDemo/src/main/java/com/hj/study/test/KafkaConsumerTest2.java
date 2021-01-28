package com.hj.study.test;


import com.google.common.collect.Lists;
import com.hj.study.constant.KafkaConstants;
import com.hj.study.consumer.strategy.ConsumerService;
import com.hj.study.consumer.strategy.ConsumerServiceStrategyFactory;
import com.hj.study.consumer.strategy.ConsumerTypeEnum;
import com.hj.study.utils.kafka.IBusinessConsumeService;
import com.hj.study.utils.kafka.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.List;


@Slf4j
@Component
@DependsOn("springContextUtils")
public class KafkaConsumerTest2 {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerTest2.class);


    @Value(("${kafka.enabled}"))
    String kafkaEnabled;

    @PostConstruct
    public void listen() {
        if ("false".equalsIgnoreCase(kafkaEnabled)) {
            return;
        }
        KafkaUtil.startConsumer(KafkaConstants.KAFKA_TEST2, KafkaConstants.KAFKA_TEST2, new IBusinessConsumeService() {
            @Override
            public void consumeList(List<ConsumerRecord<String, String>> consumerList) {
                List<String> list = Lists.newArrayList();
                for (ConsumerRecord<String, String> consumerRecord : consumerList) {
                    list.add(consumerRecord.value());
                }

                ConsumerService consumerService
                        = ConsumerServiceStrategyFactory.getByConsumerType(ConsumerTypeEnum.KAFKA_EVENT_USER.getConsumerType());
                consumerService.executeBatch(list);
            }

//            @Override
//            public void consumeRecord(ConsumerRecord<String, String> consumerRecord) {
//                log.info("从主题{}获取到消息：{}", KafkaConstants.APP_COLLECT_EVENT_TOPIC, consumerRecord.value());
//                excute(consumerRecord.value());
//            }
        });
    }


}

