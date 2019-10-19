package com.brock.smootbursty.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-09-25-15:51
 */
public class HashMapThread extends Thread {

    private static AtomicInteger ai = new AtomicInteger();
    private static Map<Integer, Integer> map = new HashMap<>();

    @Override
    public void run() {
        while (ai.get() < 1000000) {
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
            System.out.println(Thread.currentThread().getName() +": "+ ai.get());
        }
    }
}
