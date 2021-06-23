package com.hj.study.thread.waitNotify;

import com.hj.study.thread.tongbu.ThreadLock;

/**
 * @author hutao
 * @className ThreadTest
 * @description TODO
 * @date 2021/6/23 2:32 下午
 */
public class ThreadTest {

    public static class ThreadC extends Thread {
        private String lock = "";
        public ThreadC(String lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock){
                if(MyList.size()!= 5){
                    try {
                        System.out.println("开始ThreadC wait===>, myList.size=" + MyList.size());
                        lock.wait();
                        System.out.println("结束ThreadC wait===>, myList.size=" + MyList.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ThreadC, myList.size=" + MyList.size());
                }

            }
        }
    }

    public static class ThreadD extends Thread {
        private String lock = "";
        public ThreadD(String lock) {
            this.lock = lock;
        }
        @Override
        public void run() {
            super.run();
            synchronized (lock){
                for(int i=0;i<10;i++){
                    System.out.println("ThreadD执行次数，第" + (i+1) + "次");
                    MyList.add();
                    if(MyList.size() == 5){
                        lock.notify();
                        System.out.println("唤醒线程C, myList.size=" + MyList.size());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {

        String lock = "a";
        ThreadC threadC = new ThreadC(lock);
        ThreadD threadD = new ThreadD(lock);
        threadC.start();
        threadD.start();

    }
}
