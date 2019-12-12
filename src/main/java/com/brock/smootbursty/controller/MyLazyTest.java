package com.brock.smootbursty.controller;

import com.brock.smootbursty.config.MyLazyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-12-11-18:05
 */
@RestController
public class MyLazyTest {

    @Autowired
    public MyLazyConfig myLazyConfig;

    @GetMapping("testLazy")
    public  void main(String[] args) {
        myLazyConfig.myLazyTest();
        System.out.println("===>>>测试输出");
    }

}
