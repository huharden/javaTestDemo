package com.hj.study.controller;

import java.util.concurrent.*;

/**
 * @author hutao
 * @className ThreadPoolTest
 * @description 线程池测试
 * @date 2021/1/28 10:12 上午
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        //注意使用 ThreadPoolExecutor 版本，版本不同，初始化线程数配置不一样
        ThreadPoolExecutor executor= new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for(int i = 0; i< 7; i++){
            executor.execute(()->{
                System.out.println(Thread.currentThread().getName() + "正在办理业务===>");
            });
        }
        executor.shutdown();
        //ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
    }
}
