package com.hj.study.service;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @projectName：javaTestDemo
 * @className：CallbackMethod
 * @describe：TODO
 * @createTime：2020/4/14 1:44 下午
 * @author：HuTao
 */
public class CallbackMethod implements MethodInterceptor {

    /**
     * proxy: 代理对象
     * method: 目标对象中的方法
     * objects：目标对象方法的参数
     * methodProxy：代理对象中的代理方法
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        //此处可以相当于前置通知方法
        System.out.println("前置通知===>>>" + "111111");

        Object object = methodProxy.invokeSuper(proxy, objects);

        //此处可以相当于后置通知方法
        System.out.println("后置通知===>>>" + "22222");

        return object;
    }
}
