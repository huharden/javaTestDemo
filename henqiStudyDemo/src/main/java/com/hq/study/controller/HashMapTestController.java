package com.hq.study.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-09-25-15:30
 */
@RestController
@RequestMapping("HashMapTest")
public class HashMapTestController {
    private static AtomicInteger ai = new AtomicInteger();
    private static Map<Integer, Integer> map = new HashMap<>();

    @GetMapping("test1")
    @Async
    public void HashMap(@RequestParam int count){

        HashMap map = new HashMap();
        while (ai.get() < count) {
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
            System.out.println(Thread.currentThread().getName() +": "+ ai.get());
        }

    }

}
