package com.hj.study.controller;

import com.hj.study.service.InvokeService;
import com.mysql.jdbc.PreparedStatement;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @projectName：javaTestDemo
 * @className：InvokeTestController
 * @describe：TODO
 * @createTime：2020/5/22 9:28 上午
 * @author：HuTao
 */
public class InvokeTestController {

    @Test
    public void test() throws Exception {

        InvokeController invokeController = new InvokeController();
        Class<? extends InvokeController> clazz = invokeController.getClass();

        InvokeService invokeService = new InvokeService();
        System.out.println(invokeService);


        Field invokeServiceField = clazz.getDeclaredField("invokeService");
        //invokeServiceField.setAccessible(true);

        String name = invokeServiceField.getName();

        String serviceName = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());

        String methodName = "set" + serviceName;

        Method method = clazz.getMethod(methodName, InvokeService.class);
        method.invoke(invokeController, invokeService);

        System.out.println(invokeController.getInvokeService());

    }
    
    @Test
    public void test2() throws Exception {
        PreparedStatement ps = null;
        SimpleDateFormat dateFormator = new SimpleDateFormat("yyyy-mm-dd");
        Date date = dateFormator.parse("2000-01-01");
        ps.setDate(3, new java.sql.Date(date.getTime()));

    }

    
}
