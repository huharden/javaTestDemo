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

    @Override
    @Async
    public void threadPoolTest() {
        System.out.println(Thread.currentThread().getName() + "线程池测试");

    }
}
