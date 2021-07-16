package com.hj.study.gof.singleton;


/**
 * 单例模式
 * 采用类加载的模式，最完美的写法之一
 */
public class Mgr06 {

    /**
     * 私有化构造函数
     */
    private Mgr06(){}

    /**
     * 匿名内部类写法
     */
    private static class getInstanceHolder {
        private final static Mgr06 INSTANCE = new Mgr06();
    }


    public static Mgr06 getInstance() {
        return getInstanceHolder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr06.getInstance().hashCode());
            }).start();
        }
    }

}
