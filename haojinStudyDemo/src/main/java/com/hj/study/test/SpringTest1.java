package com.hj.study.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @projectName：javaTestDemo
 * @className：SpringTest1
 * @describe：TODO
 * @createTime：2020/11/28 3:16 下午
 * @author：HuTao
 */
public class SpringTest1 {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("tx.xml");

    }
}
