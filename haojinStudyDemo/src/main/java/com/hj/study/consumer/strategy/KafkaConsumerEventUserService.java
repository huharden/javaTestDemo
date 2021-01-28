package com.hj.study.consumer.strategy;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KafkaConsumerEventUserService implements ConsumerService, InitializingBean {

    @Autowired
    private MQBatchExecuteUtil mqBatchExecuteUtil;


    @Override
    public void executeBatch(List<String> msgList) {
        mqBatchExecuteUtil.webBatchExecute(msgList);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ConsumerServiceStrategyFactory.register(ConsumerTypeEnum.KAFKA_EVENT_USER.getConsumerType(), this);
    }
}

