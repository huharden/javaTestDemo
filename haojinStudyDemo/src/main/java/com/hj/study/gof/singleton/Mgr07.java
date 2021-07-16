package com.hj.study.gof.singleton;


/**
 * 单例模式
 * java创始人的写法，最完美的写法
 * 可以解决线程同步问题，还可以防止反序列化
 */
public enum Mgr07 {

    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr07.INSTANCE.hashCode());
            }).start();
        }
    }

}
