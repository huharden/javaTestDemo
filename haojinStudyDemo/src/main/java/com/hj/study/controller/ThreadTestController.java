package com.hj.study.controller;

import java.util.concurrent.locks.LockSupport;

/**
 * @projectName：javaTestDemo
 * @className：ThreadTestController
 * @describe：TODO
 * @createTime：2020/9/9 5:10 下午
 * @author：HuTao
 */
public class ThreadTestController {

    static char[] cI = "1234567".toCharArray();
    static char[] cC = "ABCDEFG".toCharArray();

    static Thread t1, t2;
    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for(char i: cI){
                System.out.print(i);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c: cC){
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
