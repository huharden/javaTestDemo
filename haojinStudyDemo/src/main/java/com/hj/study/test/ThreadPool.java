package com.hj.study.test;


import java.util.concurrent.*;

public class ThreadPool {
    private static ExecutorService pool;
    public static void main(String[] args) {
        //maximumPoolSize设置为2 ，拒绝策略为AbortPolic策略，直接抛出异常
//        pool = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue<Runnable>(30), Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());

        pool = new ThreadPoolExecutor(2, 5, 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 9; i++) {
            pool.execute(new ThreadTask());
            boolean terminated = pool.isTerminated();
            System.out.println("aaaaaaaaaa11:" + terminated);
        }
    }
}

