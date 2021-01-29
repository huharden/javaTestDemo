package com.hj.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-10-19-11:00
 */
@SpringBootApplication(scanBasePackages = "com.hj.study")
//@EnableEurekaClient
@EnableAsync
public class HaojinApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaojinApplication.class,args);
        System.out.println("TestApplication................");
    }

}
