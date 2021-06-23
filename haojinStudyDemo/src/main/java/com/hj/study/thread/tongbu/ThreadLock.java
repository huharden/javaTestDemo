package com.hj.study.thread.tongbu;

/**
 * @author hutao
 * @className ThreadLock
 * @description 线程通信测试-同步方式
 * @date 2021/6/23 1:58 下午
 */
public class ThreadLock {

    public void methodA(){
        System.out.println("methodA 开始执行。。。。");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("methodA 结束执行。。。。");
    }

    public void methodB(){
        System.out.println("methodB 开始执行。。。。");
    }
}
