package com.hj.study.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CacheUtil
 * @Description: 缓存通用工具类
 * @Author: liangkun
 * @Date: 2020/10/14 15:04
 * @Version: 1.0
 **/
@Component
public class CacheUtil {

    @Autowired
    private RedisUtil redisUtil;


    private static final String CACHE_TYPE_REDIS = "redis";


    /**
     * 向存于 key 的列表的尾部插入所有指定的值
     * @param key
     * @param value
     * @return 当前值
     */
    public long push(String group, String key, String value) {
        return redisUtil.rightPush(group, key, value);
    }

    /**
     * 获取列表中指定区间的值
     * @param key
     * @param start
     * @param end
     * @return 当前值
     */
    public List<String> range(String group, String key, long start, long end) {
        List<String> listResult = new ArrayList<>();
        listResult = redisUtil.range(group, key, start, end);
        return listResult;
    }

    /**
     * 获取列表中总数
     * @param key
     * @return 当前值
     */
    public long size(String group, String key) {
        return redisUtil.size(group, key);
    }

    /**
     * 截取列表中元素长度，保留长度内的数据（让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除）
     * @param key
     * @return 当前值
     */
    public void trim(String group, String key, long start, long end) {
        redisUtil.trim(group, key, start, end);

    }


}
