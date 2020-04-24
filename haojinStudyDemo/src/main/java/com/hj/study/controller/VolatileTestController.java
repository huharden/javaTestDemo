package com.hj.study.controller;

/**
 * @projectName：javaTestDemo
 * @className：V
 * @describe：
 * @createTime：2020/4/24 9:56 上午
 * @author：HuTao
 */
public class VolatileTestController {

    private static volatile boolean flag = true;

    //private static int i = 1;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
               while (flag){
                   //i++;
                   System.out.println("当前线程===>>>" + Thread.currentThread().getName() + "当前参数值===>>>" );
               }
                System.out.println("线程结束===>>>" + Thread.currentThread().getName());
            }
        }, "server1").start();
        Thread.sleep(1000);

        System.out.println("当前线程===>>>" + Thread.currentThread().getName());
        flag =false;



    }
}
