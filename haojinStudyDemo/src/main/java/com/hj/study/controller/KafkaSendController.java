package com.hj.study.controller;

import com.hj.study.utils.ProducerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName：javaTestDemo
 * @className：KafkaSendcontroller
 * @describe：TODO
 * @createTime：2020/4/13 5:04 下午
 * @author：HuTao
 */
@RestController
@RequestMapping("/kafka")
public class KafkaSendController {

    @Autowired
    private ProducerUtil producer;

    @RequestMapping(value = "/send")
    public String send() {
        producer.send();
        return "{\"code\":0}";
    }
}