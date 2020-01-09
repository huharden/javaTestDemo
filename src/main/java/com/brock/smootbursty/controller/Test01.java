package com.brock.smootbursty.controller;


/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-12-27-11:44
 */
public class Test01{

    static long value = 0l;

    String box1 = "a";
    String box2 = "b";

     long get() {
        synchronized(box1){
            return value;
        }


    }

     void add() {
         synchronized(box1){

                 value += 1;

         }



    }


    public static void main(String[] args) {

        Test01 test01 = new Test01();


        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.add();

                System.out.println("aaaa===>>>"+test01.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long b = test01.get();
                b = b + 1;
                System.out.println("bbbb===>>>"+b);
            }
        }).start();

    }


}
