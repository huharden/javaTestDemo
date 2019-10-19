package com.brock.smootbursty.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * @author: hutao
 * @date 2019/9/29 14:21
 */
@Configuration
public class RabbitConfig {
    /**
     * 定义个direct交换器
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        // 定义一个名为fanoutExchange的fanout交换器
        return new DirectExchange("HQ_PAY_EXCHANGE",true,false);
    }

    /**
     * 定义hangJiaQueue队列
     *
     * @return
     */

    @Bean
    public Queue paymentQueue() {
        return new Queue("HQ_GUANGWANG_TEST",true,false,false);
    }

    /**
     * 将定义的topicC队列与topicExchange交换机绑定
     * 绑定行家路由-队列
     * @return
     */
    @Bean
    public Binding paymentBinding() {
        return BindingBuilder.bind(paymentQueue()).to(directExchange()).with("HQ_GUANG_WANG");
    }
}
