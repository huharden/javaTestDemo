package com.hj.study.consumer.strategy;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum ConsumerTypeEnum {

    KAFKA_EVENT_USER(1, "KAFKA_EVENT_USER");

    private int code;

    private String consumerType;

    public static ConsumerTypeEnum getEnumByType(String type) {
        Optional<ConsumerTypeEnum> optional = Stream.of(ConsumerTypeEnum.values())
                .filter(val -> val.consumerType.equals(type))
                .findFirst();
        return optional.orElse(null);
    }
}

