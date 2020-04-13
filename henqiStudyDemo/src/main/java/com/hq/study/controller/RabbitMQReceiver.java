package com.hq.study.controller;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-09-29-11:30
 */
@Component
public class RabbitMQReceiver {


    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(value = "HQ_GUANGWANG_TEST", durable = "true", exclusive = "false", autoDelete = "false"),
                            exchange = @Exchange(value = "HQ_PAY_EXCHANGE", durable = "true")
                    )
            }
    )
    @RabbitHandler
    public void process(Message msg, Channel channel) throws IOException {
        try {

            System.out.println("Receiver : " + msg + "get!!!");
        }catch (Exception e){
            System.out.println("系统异常"+ e);
        }finally {
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(),true);
        }
    }
}
