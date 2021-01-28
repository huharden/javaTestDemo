package com.hj.study.controller;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author hutao
 * @className ObjectJolTest
 * @description 内存中的内存布局问题
 * @date 2021/1/28 2:16 下午
 */
public class ObjectJolTest {

    public static void main(String[] args) {
        Object o = new Object();

        //内存布局打印
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
