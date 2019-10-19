package com.brock.smootbursty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-10-19-11:00
 */
@SpringBootApplication(scanBasePackages = "com.brock.smootbursty")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
        System.out.println("TestApplication................");
    }

}
