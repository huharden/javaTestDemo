package com.hj.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @author: liangkun
 * @date: 2020-07-28 16:34
 * @description:
 */
@RestController
@RequestMapping("/testController")
public class ScheduleTestController {

    @Autowired
    private ScheduleJobTest scheduleJobTest;

    @PostMapping("/setCron")
    public String setCron(){
        scheduleJobTest.setCron("0/10 * * * * ?");
        return "success";
    }

}
