package com.brock.smootbursty.controller;


import java.util.HashMap;
import java.util.HashSet;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-12-27-11:44
 */
public class Test02 extends Thread{

    static long value = 0l;

    String box1 = "a";
    String box2 = "b";
    Thread thread01 = new Thread();

    @Override
    public void run(){
       Integer num =  count(1);
        System.out.println("测试===>>>" + num);
    }

    public Integer count(int i){
        Integer num = 0;
        if(i==0){
            num = 0;
        }else if(i == 1){
            num = 5;
        }else {
            num = count(i-1) + count(i-2);
        }

        return num;
    }

    public static void main(String[] args) {
        Test02 test02 = new Test02();
        test02.start();

        HashSet set = new HashSet();
        set.add(1);
        set.add(2);
        System.out.println(set);
    }


}
