package com.hj.study.controller;

import com.hj.study.utils.ThreadLocalUtil;

/**
 * @projectName：javaTestDemo
 * @className：IndexThread
 * @describe：TODO
 * @createTime：2020/8/8 4:49 下午
 * @author：HuTao
 */
public class IndexThread {
    public static void main(String[] args) {
        test5();
    }

    /**
     * 饿汉模式
     */
    public static void  test1(){
        ThreadLocalUtil.setValue("aa", "bb");
    }

    public static void  test2(){
        Object aa = ThreadLocalUtil.getValue();
        ThreadLocalUtil.clear();
        System.out.println("饿汉模式===>>>" + aa);
    }

    /**
     * 懒汉模式
     */
    public static void test3(){
        ThreadLocal local = new ThreadLocal();
        local.set("aa");
    }

    public static void test4(){
        ThreadLocal local = new ThreadLocal();
        System.out.println("懒汉模式===>>>" + local.get());
    }

    /**
     * 启动线程的几种方式
     *  函数式接口
     */
    public static void test5(){
        Thread thread =new Thread(()->{
            System.out.println("测试");
        });

        thread.start();
        System.out.println("lllll");
        Thread.dumpStack();
    }

    /**
     * 启动线程的几种方式
     *  方法内实现
     */
    public static void test6(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试");
            }
        });
        thread.start();
    }
}
