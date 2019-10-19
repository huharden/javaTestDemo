package com.brock.smootbursty.anno;

import java.lang.annotation.*;

/**
* @Description: AOP
* @Author: Brock.lee
* @Date: 2019/6/28
**/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopTest {

    String value() default "";
}
