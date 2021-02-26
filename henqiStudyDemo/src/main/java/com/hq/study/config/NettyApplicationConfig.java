package com.hq.study.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author hutao
 * @className NettyApplicationConfig
 * @description netty ws启动类
 * @date 2021/2/26 1:52 下午
 */
@Component
public class NettyApplicationConfig implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 重写onApplicationEvent方法
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //如果应用启动成功，则自动启动ws
        if(contextRefreshedEvent.getApplicationContext().getParent() != null){

        }

    }
}
