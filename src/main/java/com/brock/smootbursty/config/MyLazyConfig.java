package com.brock.smootbursty.config;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-12-11-18:05
 */
@Component
@Lazy
public class MyLazyConfig {

    public MyLazyConfig(){
        System.out.println("===>>>开始工作");
    }

    

    public void myLazyTest(){
        System.out.println("===>>>测试");
    }

}
