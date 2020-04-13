package com.hj.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-10-19-11:00
 */
@SpringBootApplication(scanBasePackages = "com.hj.study")
public class HaojinApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaojinApplication.class,args);
        System.out.println("TestApplication................");
    }

}
