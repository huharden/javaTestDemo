package com.hj.study.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hj.study.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @projectName：javaTestDemo
 * @className：ProducerUtil
 * @describe：TODO
 * @createTime：2020/4/13 4:58 下午
 * @author：HuTao
 */
@Component
public class ProducerUtil {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId("KF_"+System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(LocalDateTime.now());

        // 指定topic sl_test
        System.out.println("发送信息===>>>"+ gson.toJson(message));
        kafkaTemplate.send("sl_test", gson.toJson(message));
    }

}
