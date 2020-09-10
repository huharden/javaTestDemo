package com.hj.study.controller;

/**
 * @projectName：javaTestDemo
 * @className：ThreadTestController
 * @describe：TODO
 * @createTime：2020/9/9 5:10 下午
 * @author：HuTao
 */
public class LockThreadController {

    static final Object o = new Object();

    static char[] cI = "1234567".toCharArray();
    static char[] cC = "ABCDEFG".toCharArray();

    static Thread t1, t2;
    public static void main(String[] args) {
        t1 = new Thread(() -> {
            synchronized (o){
                for(char i: cI){
                    System.out.print(i);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }, "t1");

        t2 = new Thread(() -> {
            synchronized (o){
                for (char c: cC){
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }, "t2");

        t1.start();
        t2.start();
    }
}
