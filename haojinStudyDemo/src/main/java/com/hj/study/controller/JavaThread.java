package com.hj.study.controller;

/**
 * @author hutao
 * @className JavaThread
 * @description TODO
 * @date 2021/6/10 5:18 下午
 */
public class JavaThread implements Runnable{

    public static void main(String[] args) {
        new JavaThread().run();
        System.out.println("thread run");
    }

    @Override
    public void run() {
        System.out.println("sub thread run");
    }
}
