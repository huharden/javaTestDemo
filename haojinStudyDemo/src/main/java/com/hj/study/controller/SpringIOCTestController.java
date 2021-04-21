package com.hj.study.controller;

import com.hj.study.service.SpringTestService;
import com.hj.study.service.impl.SpringTestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hutao
 * @className SpringIOCTestController
 * @description TODO
 * @date 2021/4/21 9:19 上午
 */
@RestController
@RequestMapping("/ioc")
public class SpringIOCTestController {

    @Autowired
    private SpringTestService springTestService;

    @PostMapping("/getTest1")
    public void getTest1(){
        springTestService.staticTest();
    }

    @PostMapping("/getTest2")
    public void getTest2(){
        String globalVariable = SpringTestServiceImpl.GLOBAL_VARIABLE;
        System.out.println(globalVariable);
    }



}
