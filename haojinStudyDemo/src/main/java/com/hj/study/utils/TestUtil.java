package com.hj.study.utils;

import org.springframework.context.annotation.Configuration;

/**
 * @author hutao
 * @className TestUtil
 * @description TODO
 * @date 2021/4/21 9:34 上午
 */
@Configuration
public class TestUtil {
    public static String GLOBAL_VARIABLE = "TestUtil===>全局静态变量";

    static {
        System.out.println(GLOBAL_VARIABLE);
    }
}
