package com.hj.study.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName：data-platform
 * @className：SysSdk
 * @describe：埋点采集
 * @createTime：2020/7/24 9:22 上午
 * @author：HuTao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysSdk {
    String value() default "";
}
