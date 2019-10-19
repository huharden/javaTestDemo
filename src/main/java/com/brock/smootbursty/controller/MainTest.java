package com.brock.smootbursty.controller;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-09-16-17:39
 */
public class MainTest {

    public static void main(String[] args){
        HashMapThread thread0 = new HashMapThread();
        HashMapThread thread1 = new HashMapThread();
        HashMapThread thread2 = new HashMapThread();
        HashMapThread thread3 = new HashMapThread();
        HashMapThread thread4 = new HashMapThread();
        HashMapThread thread5 = new HashMapThread();

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

}

