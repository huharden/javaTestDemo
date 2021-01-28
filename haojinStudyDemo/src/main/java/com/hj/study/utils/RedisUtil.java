package com.hj.study.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RedisUtil
 * @Description: 缓存工具类
 * @Author: liangkun
 * @Date: 2020/10/10 17:35
 * @Version: 1.0
 **/
@Component
public class RedisUtil {
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 向key列表右边插入redis值
     * @param key
     * @param value
     * @return
     */
    public long rightPush(String group, String key, String value) {
        try {
            return stringRedisTemplate.opsForList().rightPush(group + "::" + key, value);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 获取指定区间的值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> range(String group, String key, long start, long end) {
        List<String> listResult = new ArrayList<>();
        try {
            listResult = stringRedisTemplate.opsForList().range(group + "::" + key, start, end);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return listResult;
    }

    /**
     * 获取总数
     * @param key
     * @return
     */
    public long size(String group, String key) {
        try {
            return stringRedisTemplate.opsForList().size(group + "::" + key);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 截取列表中元素长度，保留长度内的数据（让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除）
     * @param key
     * @return
     */
    public void trim(String group, String key, long start, long end) {
        try {
            stringRedisTemplate.opsForList().trim(group + "::" + key, start, end);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public   void test() {
        //向存于 key 的列表的尾部插入所有指定的值
        String key = "test";
        stringRedisTemplate.delete(key);
        int  end = 5;
        for(int i = 1; i <= 10; i++){
            stringRedisTemplate.opsForList().rightPush(key, String.valueOf(i));
        }
        logger.info("初始化列表："+ key + "完毕:总数" + stringRedisTemplate.opsForList().size(key));
        List<String> listResult =  stringRedisTemplate.opsForList().range(key, 0, end);
        logger.info("取数列表：{}完毕:总数{}" ,listResult ,  stringRedisTemplate.opsForList().size(key));
        logger.info("删除后的列表数据");
        stringRedisTemplate.opsForList().trim(key, end, -1); //让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
        List<String> listResultTotal =  stringRedisTemplate.opsForList().range(key, 0, -1);
        logger.info("删除后的列表数据：{}完毕:总数{}" ,listResultTotal ,  stringRedisTemplate.opsForList().size(key));
/*
        jedis.rpush();
        jedis.lrange();
        jedis.ltrim();
        jedis.llen();*/


    }

}
