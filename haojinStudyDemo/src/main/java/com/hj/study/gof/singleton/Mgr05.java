package com.hj.study.gof.singleton;

import org.joda.time.DateTime;


/**
 * 单例模式 懒汉式
 * 加双重锁解决线程不安全问题
 * 最完美的写法
 */
public class Mgr05 {
    //避免指令重排
    private static volatile Mgr05 INSTANCE ;

    /**
     * 私有化构造函数
     */
    private Mgr05(){}

    /**
     * 双重锁判断
     * @return
     */
    public static Mgr05 getInstance(){
        if (INSTANCE == null) {
            synchronized (Mgr05.class){
                if(INSTANCE == null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr05();
                }
            }

        }
        return INSTANCE;
    }

    /**
     * 线程不安全测试
     * @param args
     */
    public static void main(String[] args) {
        long startTime = new DateTime().getMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr05.getInstance().hashCode());
            }).start();
        }
        long endTime = new DateTime().getMillis();
        System.out.println("耗时===> "+ (endTime - startTime));
    }

}
