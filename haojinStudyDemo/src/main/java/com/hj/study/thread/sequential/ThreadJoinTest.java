package com.hj.study.thread.sequential;

/**
 * @author hutao
 * @className ThreadTest
 * @description TODO
 * @date 2021/6/23 3:15 下午
 */
public class ThreadJoinTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            System.out.println("产品经理规划需求");
        });

        Thread thread2 = new Thread(()->{
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("程序员开始开发需求");
        });

        Thread thread3 = new Thread(()->{
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("测试人员开始测试需求");
        });


        System.out.println("测试人员开始上班了");
        thread3.start();
        System.out.println("程序员开始上班了");
        thread2.start();
        System.out.println("产品经理开始上班了");
        thread1.start();

    }
}
