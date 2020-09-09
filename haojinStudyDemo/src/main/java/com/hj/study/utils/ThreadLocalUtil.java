package com.hj.study.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName：javaTestDemo
 * @className：ThreadLocalUtil
 * @describe：TODO
 * @createTime：2020/8/10 2:15 下午
 * @author：HuTao
 */
public class ThreadLocalUtil {

    private static ThreadLocal<Map<String, Object>> local = new ThreadLocal<>();

    public static Object getValue(){
        return local.get();
    }

    public static Object getValue(String key){
        Map<String, Object> resultMap = local.get();
        return resultMap.get(key);
    }

    public static void setValue(String key, Object value){
        Map<String, Object> resultMap = local.get();
        if(resultMap == null){
            resultMap = new HashMap<>();
        }

        resultMap.put(key, value);
        local.set(resultMap);
    }

    public static void clear(){
        local.remove();
    }
}
