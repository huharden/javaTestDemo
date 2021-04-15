package com.hj.study.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hutao
 * @className CounterThreadTest
 * @description 全局变量测试
 * @date 2021/3/5 9:56 上午
 */
public class CounterThreadTest {

    public Map<String, Object> map = new HashMap<>();

    public void putMap(String msg){
        try {
            map.put("currentMap", msg);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getMap() {
        System.out.println(Thread.currentThread().getName() + "输出数据为===>" + map.get("currentMap"));
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                CounterThreadTest counterThreadTest = new CounterThreadTest();
                counterThreadTest.putMap("我是天才1号");
                counterThreadTest.getMap();
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                CounterThreadTest counterThreadTest = new CounterThreadTest();
                counterThreadTest.putMap("我是天才2号");
                counterThreadTest.getMap();
            }
        };
        Thread thread3 = new Thread(){
            @Override
            public void run() {
                CounterThreadTest counterThreadTest = new CounterThreadTest();
                counterThreadTest.putMap("我是天才3号");
                counterThreadTest.getMap();
            }
        };
        Thread thread4 = new Thread(){
            @Override
            public void run() {
                CounterThreadTest counterThreadTest = new CounterThreadTest();
                counterThreadTest.putMap("我是天才4号");
                counterThreadTest.getMap();
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
