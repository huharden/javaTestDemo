/*
package com.hj.study;

import com.hj.study.netty.NettyWSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

*/
/**
 * @author hutao
 * @className NettyBootstrap
 * @description netty 启动类
 * @date 2021/2/26 3:48 下午
 *//*

@Component("BeanDefineConfig")
public class NettyBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //如果应用启动完成,则启动 socket
        if (event.getApplicationContext().getParent() == null) {
            try {
                NettyWSServer.getInstance().start(6668);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
*/
