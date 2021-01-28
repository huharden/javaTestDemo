package com.hj.study.consumer.strategy;


import java.util.List;

/**
 *  消费者策略模式
 */
public interface ConsumerService {

    void executeBatch(List<String> msgList);


}

