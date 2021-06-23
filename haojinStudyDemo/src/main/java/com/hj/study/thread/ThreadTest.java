package com.hj.study.thread;

import jodd.util.StringUtil;

/**
 * @author hutao
 * @className ThreadTest
 * @description 线程通信-同步
 * @date 2021/6/23 1:59 下午
 */
public class ThreadTest {

    public static class ThreadA extends Thread {
        private String lock = "";
        public ThreadA(String lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            super.run();
            ThreadLock threadLock = new ThreadLock();
            synchronized (lock){

                threadLock.methodA();
            }
        }
    }

    public static class ThreadB extends Thread {
        private String lock = "";
        public ThreadB(String lock) {
            this.lock = lock;
        }
        @Override
        public void run() {
            super.run();
            ThreadLock threadLock = new ThreadLock();
            synchronized (lock){

                threadLock.methodB();
            }
        }
    }


        public static void main(String[] args) {

            String lock = "a";
            ThreadA threadA = new ThreadA(lock);
            ThreadB threadB = new ThreadB(lock);
            threadA.start();
            threadB.start();

        }

}
