package com.hj.study.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hj.study.dto.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @projectName：javaTestDemo
 * @className：ConsumerConfig
 * @describe：TODO
 * @createTime：2020/4/13 5:06 下午
 * @author：HuTao
 */
//@Component
public class ConsumerConfig {

    @KafkaListener(topics = {"sl_test"})
    public void listen(ConsumerRecord<?, ?> record){

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            System.out.println("---->"+record);
            System.out.println("---->"+message);

            Message m = JSON.parseObject(JSONObject.toJSONString(message), Message.class);
            System.out.println("转成对象后。。" + m);
        }

    }
}
