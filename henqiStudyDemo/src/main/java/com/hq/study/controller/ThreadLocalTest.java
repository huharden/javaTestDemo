package com.hq.study.controller;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-10-23-9:50
 */
public class ThreadLocalTest {

    public static void main(String[] args){
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("测试");
        local.set("测试2");
        System.out.println(local.get());
    }
}
