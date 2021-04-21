package com.hj.study.service.impl;

import com.hj.study.service.SpringTestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author hutao
 * @className SpringTestServiceImpl
 * @description TODO
 * @date 2021/1/29 3:28 下午
 */
@Service
public class SpringTestServiceImpl implements SpringTestService {

    static {
        System.out.println("这是一个静态方法区");
    }

    public static String GLOBAL_VARIABLE = "全局静态变量";

    @Override
    @Async
    public void threadPoolTest() {
        System.out.println(Thread.currentThread().getName() + "线程池测试");

    }

    @Override
    public void staticTest() {
        GLOBAL_VARIABLE = "静态方法=>hello!";
        System.out.println(GLOBAL_VARIABLE);

    }
}
