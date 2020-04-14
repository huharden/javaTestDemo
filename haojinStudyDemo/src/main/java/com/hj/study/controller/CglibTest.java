package com.hj.study.controller;

import com.hj.study.service.CallbackMethod;
import com.hj.study.service.Student;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @projectName：javaTestDemo
 * @className：CglibTest
 * @describe：TODO
 * @createTime：2020/4/14 1:37 下午
 * @author：HuTao
 */
public class CglibTest {


    /**
     * cglib实现动态代理步骤
     * 1 生成字节码对象（空的）Enhancer
     * 2 设定这个字节码对象的父类
     * 3 设置增强的方法、回调方法、拦截方法
     * 4 得到代理对象
     *
    */
    public static void main(String[] args) {
        Enhancer en = new Enhancer();
        en.setSuperclass(Student.class);

        Callback callback = new CallbackMethod();
        en.setCallback(callback);

        Student student = (Student) en.create();

        student.study();


    }
}
