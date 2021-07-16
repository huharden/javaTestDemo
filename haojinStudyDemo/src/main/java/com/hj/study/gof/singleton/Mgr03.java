package com.hj.study.gof.singleton;

/**
 * 单例模式 懒汉式
 * 容易导致线程不安全
 */
public class Mgr03 {
    private static Mgr03 INSTANCE ;

    /**
     * 私有化构造函数
     */
    private Mgr03(){}

    public static Mgr03 getInstance(){
        if (INSTANCE == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    /**
     * 线程不安全测试
     *  当判断instance == null,并且上一个线程对象还未创造出来，即可能出现线程不安全
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr03.getInstance().hashCode());
            }).start();
        }
    }

}
