package com.hj.study.gof.singleton;

/**
 * 单例模式
 * 缺点：每次类加载都会创建实例
 * 但是由于线程安全，使用最常见
 */
public class Mgr01 {
    private static Mgr01 INSTANCE = new Mgr01();

    /**
     * 私有化构造函数
     */
    private Mgr01(){}

    public static Mgr01 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        Mgr01 mgr01 = getInstance();
        Mgr01 mgr02 = getInstance();
        System.out.println(mgr01 == mgr02);
    }

}
