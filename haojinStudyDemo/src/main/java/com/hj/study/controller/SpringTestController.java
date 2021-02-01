package com.hj.study.controller;

import com.hj.study.service.SpringTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName：javaTestDemo
 * @className：SpringTestController
 * @describe：TODO
 * @createTime：2020/11/26 7:42 下午
 * @author：HuTao
 */
@RestController
@RequestMapping("/threadPool")
public class SpringTestController {

    @Autowired
    private SpringTestService springTestService;

    @PostMapping("/async")
    public void test(){
        System.out.println("线程池测试===>");
        for (int i = 0; i < 5; i++) {
            springTestService.threadPoolTest();
        }
    }


}
