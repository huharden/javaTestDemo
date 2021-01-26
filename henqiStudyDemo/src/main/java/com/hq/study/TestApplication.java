package com.hq.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-10-19-11:00
 */
@SpringBootApplication(scanBasePackages = "com.hq.study")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
        System.out.println("TestApplication...................");
    }

}
