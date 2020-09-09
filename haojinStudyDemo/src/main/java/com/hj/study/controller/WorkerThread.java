package com.hj.study.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @projectName：javaTestDemo
 * @className：WorkerThread
 * @describe：TODO
 * @createTime：2020/8/8 4:48 下午
 * @author：HuTao
 */
public class WorkerThread extends Thread{
    private static ThreadLocal<String> httpSessionIdLocal = new ThreadLocal<>();

    ThreadLocal<String> timeStamp = new ThreadLocal<String>(){
        protected String initialValue() {
            SimpleDateFormat sf  = new SimpleDateFormat("YYYY-MM-dd HH:mm:SS");
            return sf.format(new Date());
        }
    };
    public void run() {
        httpSessionIdLocal.set("7383hdjdfjdsbuwieiwn-09888");
        System.out.println(getName()+":"+"线程启动于"+ timeStamp.get());
        //ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //HttpServletRequest request = attrs.getRequest();
        //HttpSession session = request.getSession();
        httpSessionIdLocal.get();
        System.out.println(getName()+":"+"线程启动sessionId: ");
    }
}


