package com.hj.study.utils;

import java.util.UUID;

/**
 * @author zhouyibin
 * @desc 生成分布式随机id
 */
public class SnowflakeUtils {


    public static String nextId() {
        IdWorker idWorker = IdWorker.INSTANCE();
        //通过雪花算法获取随机id
        String id = String.valueOf(idWorker.nextId());
        //取UUID前8位
        String randomUUID = UUID.randomUUID().toString().substring(0, 7).toUpperCase();

        return randomUUID + id;
    }
}
