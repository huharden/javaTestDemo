package com.hj.study.utils.kafka.thread;

/**
 * 默认线程中断方法stop()和interrupt()使用起来都有问题，不适合项目使用。
 * stop()方法强制结束线程，如果线程处于jni调用native方法时可能会出现内存异常，导致应用奔溃
 * interrupt()方法如果被内部不知名方法try/catch并消化InterruptException，则此时无法中断该线程，该线程也不知道自己需要中断
 * 目前通过轮询stop状态判断自己是否需要结束线程
 */
public abstract class SafeInterruptThread extends Thread {

    private boolean stop = false;

    public synchronized boolean isStop() {
        return stop;
    }

    public synchronized void stopThread() {
        setStop(true);
    }

    private synchronized void setStop(boolean stop) {
        this.stop = stop;
        //调用interrupt加快线程终止，避免一些长时间的阻塞
        this.interrupt();
    }

    public SafeInterruptThread(String threadName) {
        super(Thread.currentThread().getName() + "_" + threadName);
    }

    /**
     * 判断线程是否存活
     * @return
     */
    public boolean checkThreadAlive() {
        return this.isAlive();
    }
}

