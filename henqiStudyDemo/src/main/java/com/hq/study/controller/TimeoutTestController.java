package com.hq.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-09-27-16:33
 */
@RestController
@RequestMapping("/timeouts")
public class TimeoutTestController {

    public void test01(){
        ArrayList list = new ArrayList();
        list.add(1);
    }

}
