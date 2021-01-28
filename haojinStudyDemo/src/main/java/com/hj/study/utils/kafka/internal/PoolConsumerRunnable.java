package com.hj.study.utils.kafka.internal;


import com.hj.study.utils.kafka.IBusinessConsumeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.List;

@Slf4j
public class PoolConsumerRunnable implements Runnable {

    private ConsumerRecord<String, String> consumerRecord;

    private List<ConsumerRecord<String, String>> consumerList;

    private IBusinessConsumeService businessConsumeService;

    public PoolConsumerRunnable(List<ConsumerRecord<String, String>> consumerList, IBusinessConsumeService businessConsumeService) {
        this.consumerList = consumerList;
        this.businessConsumeService = businessConsumeService;
    }

    @Override
    public void run() {
        try {
            businessConsumeService.consumeList(consumerList);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

