package com.brock.smootbursty.model;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@Configuration
public class KafkaTopicProperties implements Serializable {

    @Value("${kafka.topic.groupId}")
    private String groupId;

    @Value("${kafka.topic.topicName}")
    private String topicName;


}
