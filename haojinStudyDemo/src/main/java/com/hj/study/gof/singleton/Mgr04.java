package com.hj.study.gof.singleton;

/**
 * 单例模式 懒汉式
 * 加同步锁解决线程不安全问题
 */
public class Mgr04 {
    private static Mgr04 INSTANCE ;

    /**
     * 私有化构造函数
     */
    private Mgr04(){}

    /**
     * 加锁
     * @return
     */
    public static synchronized Mgr04 getInstance(){
        if(INSTANCE == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

    /**
     * 线程不安全测试
     * @param args
     */
    public static void main(String[] args) {
        for(int i =0; i<100; i++){
            new Thread(()->{
                System.out.println(Mgr04.getInstance().hashCode());
            }).start();
        }
    }

}
