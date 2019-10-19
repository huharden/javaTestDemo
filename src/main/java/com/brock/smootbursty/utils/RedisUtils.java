package com.brock.smootbursty.utils;


import com.google.common.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-17 21:12
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    /**
     * 默认过期时长，单位：秒
     */
    private final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    private final static long NOT_EXPIRE = -1;

    /**
     * 设置键值对，并设置TTL
     */
    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value, Date date) {
        valueOperations.set(key, toJson(value));
        if (date != null) {
            redisTemplate.expireAt(key, date);
        }
    }

    /**
     * 设置键值对，默认TTL
     */
    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 获取对象，并设置TTL
     */
    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    /**
     * 获取对象
     */
    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    /**
     * TypeToken 获取对象，并设置TTL
     */
    public <T> T get(String key, TypeToken typeToken, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : JsonUtil.fromJson(value, typeToken.getType());
    }

    /**
     * TypeToken 获取对象
     */
    public <T> T get(String key, TypeToken typeToken) {
        return get(key, typeToken, NOT_EXPIRE);
    }

    /**
     * 获取value字符串，并设置TTL
     */
    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 获取value字符串
     */
    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    /**
     * 删除key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 清除以key为前缀的所有ke
     */
    public String cleanKeys(String key) {
        Set<String> keys = redisTemplate.keys(key);
        for (String k : keys) {
            redisTemplate.delete(k);
        }
        return keys.toString();
    }

    /**
     * 获取以key为前缀的所有key
     */
    public Set<String> getKeys(String key) {
        return redisTemplate.keys(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JsonUtil.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return JsonUtil.fromJson(json, clazz);
    }

    /**
     * 清除key
     */
    public String cleanKey(String key) {
        Set<String> keys = redisTemplate.keys(key);
        for (String k : keys) {
            redisTemplate.delete(k);
        }
        return keys.toString();
    }

    /**
     * key数值增长
     *
     * @param key  key
     * @param span 增长跨度
     * @return
     */
    public void incr(String key, Long span) {
        redisTemplate.opsForValue().increment(key, span);
    }

    /**
     * 根据集合ID，批量查询redis
     * @param keys
     * @return
     * 返回结果的顺序，按照传入参数的顺序
     */
    public List<Object> multiGet(List<String> keys){
        return this.redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * key数值增长
     *
     * @param key  key
     * @param span 增长跨度
     * @return
     */
    public void incr(String key, Double span) {
        redisTemplate.opsForValue().increment(key, span);
    }

    /**
     * key数值增长
     *
     * @param key key
     * @return
     */
    public long increment(String key) {
        return valueOperations.increment(key, 1L);
    }


    public long lpush(String key, Object value) {
        return listOperations.leftPush(key, value);
    }

    /**
     * list集合操-新增
     * @param key
     * @param value
     * @return
     */
    public long lRPush(String key, Object value) {
        return listOperations.rightPush(key, value);
    }
    /**
     * list集合操作-删除
     * @param key
     * @param value
     * @return
     */
    public long lRemove(String key, Object value) {
        return listOperations.remove(key, 0, value);
    }

    /**
     * list集合操作-根据key取值
     * @param key
     * @return
     */
    public List<Object> lRange(String key){
        return listOperations.range(key, 0, -1);
    }

    public Object rpop(String key) {
        return listOperations.rightPop(key);
    }
}
