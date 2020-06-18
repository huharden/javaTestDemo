package com.hj.study.controller;

import com.hj.study.service.InvokeService;

/**
 * @projectName：javaTestDemo
 * @className：InvokeController
 * @describe：TODO
 * @createTime：2020/5/22 9:27 上午
 * @author：HuTao
 */
public class InvokeController {

    private InvokeService invokeService;

    public InvokeService getInvokeService() {
        return invokeService;
    }

    public void setInvokeService(InvokeService invokeService) {
        this.invokeService = invokeService;
    }
}
