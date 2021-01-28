package com.hj.study.consumer.strategy;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.util.List;


/**
 * 消息队列批量处理工具类
 */
@Slf4j
@Component
public class MQBatchExecuteUtil {
    private static Logger logger = LoggerFactory.getLogger(MQBatchExecuteUtil.class);

    /**
     * Web 批量处理方法
     *
     * @param list 消息集合
     */
    public void webBatchExecute(List<String> list) {
        System.out.println("消息开始解析");
    }


}
