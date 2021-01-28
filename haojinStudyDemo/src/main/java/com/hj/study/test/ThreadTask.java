package com.hj.study.test;


import java.util.Date;

public class ThreadTask implements Runnable {
    public ThreadTask() {

    }

    public void run() {
        try {
            Thread.sleep(4000);
            System.out.println(new Date() + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

