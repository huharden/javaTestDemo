package com.hj.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * @projectName：javaTestDemo
 * @className：RedisClusterUtils
 * @describe：TODO
 * @createTime：2020/4/28 9:58 上午
 * @author：HuTao
 */
@Slf4j
//@Component
public class RedisClusterUtils {

    @Autowired
    private JedisCluster jedisCluster;

    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    public String get(String key) {
        return jedisCluster.get(key);
    }

    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    public Long hdel(String key, String... field) {
        return jedisCluster.hdel(key, field);
    }

}
