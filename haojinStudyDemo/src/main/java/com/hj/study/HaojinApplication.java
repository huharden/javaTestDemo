package com.hj.study;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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
public class HaojinApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(HaojinApplication.class,args);
        System.out.println("TestApplication................");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(HaojinApplication.class).bannerMode(Banner.Mode.OFF);
    }

}
