package com.hj.study.netty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * @author hutao
 * @className NettyBootstrap
 * @description netty 启动类
 * @date 2021/2/26 3:48 下午
 */

@Component("BeanDefineConfig")
@Slf4j
public class NettyBootstrap implements ServletContextListener {
    /** 注入NettyServer */
    @Autowired
    private NettyWSServer nettyServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ServletContex初始化...");

        Thread thread = new Thread(new NettyServerThread());
        // 启动netty服务
        thread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    /**
     * Netty 服务启动线程
     */
    private class NettyServerThread implements Runnable {

        @Override
        public void run() {
            nettyServer.run();
        }
    }
}
