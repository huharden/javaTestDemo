package com.hq.study.controller;

import com.hq.study.service.JDKProxyTestInterface;
import com.hq.study.service.JDKProxyTestInvocationHandler;
import com.hq.study.service.impl.JDKProxyTestInterfaceImpl;
import java.lang.reflect.Proxy;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-10-21-14:20
 */
public class JDKProxyMain {


    public static void main(String[] args) {
        JDKProxyTestInterface target = new JDKProxyTestInterfaceImpl();
        // 根据目标对象创建代理对象
        JDKProxyTestInterface proxy = (JDKProxyTestInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new JDKProxyTestInvocationHandler(target));
        // 调用代理对象方法
        proxy.testProxy();
    }


}
