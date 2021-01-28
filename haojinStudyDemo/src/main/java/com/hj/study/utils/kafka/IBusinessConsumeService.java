package com.hj.study.utils.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;

import java.util.List;

public interface IBusinessConsumeService {
    void consumeList(List<ConsumerRecord<String, String>> consumerList);

    default boolean needConsume(TopicPartition topicPartition) {
        return true;
    }
}

