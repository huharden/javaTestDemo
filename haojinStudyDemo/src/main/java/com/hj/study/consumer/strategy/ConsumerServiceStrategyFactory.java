package com.hj.study.consumer.strategy;


import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂类
 */
public class ConsumerServiceStrategyFactory {

    private static Map<String, ConsumerService> services = new ConcurrentHashMap<>(6);

    public static ConsumerService getByConsumerType(String type) {
        return services.get(type);
    }

    public static void register(String consumerType, ConsumerService consumerService) {
        Assert.notNull(consumerType, "consumerType can't be null");
        services.put(consumerType, consumerService);
    }


}

