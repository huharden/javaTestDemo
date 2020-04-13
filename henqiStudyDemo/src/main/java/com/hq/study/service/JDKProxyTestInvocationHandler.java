package com.hq.study.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-10-21-14:23
 */
public class JDKProxyTestInvocationHandler implements InvocationHandler {
    private  Object target;
    public JDKProxyTestInvocationHandler(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method,
                         Object[] args) throws Throwable {
        System.out.println("执行前");
        Object result=  method.invoke(this.target,args);
        System.out.println("执行后");
        return result;
    }
}


