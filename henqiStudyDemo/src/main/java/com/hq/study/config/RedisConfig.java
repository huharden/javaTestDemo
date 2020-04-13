package com.hq.study.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * Redis配置
 *
 * @author : heshuangshuang
 * @date : 2018/1/20 10:10
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 默认缓存时长
     */
    private static final long DEFAULTEXPIRETIME = 600;

    @Resource
    private RedisConnectionFactory factory;

    /**
     * 缓存管理器
     *
     * @Cacheable(value = "user", key = "#root.targetClass.getCanonicalName()", unless = "#result eq null")
     * @CachePut
     * @CacheEvict
     */
//    @Bean
//    public CacheManager cacheManager(RedisTemplate cacheRedisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(cacheRedisTemplate);
//        //设置缓存过期时间
//        cacheManager.setDefaultExpiration(DEFAULTEXPIRETIME);
//        cacheManager.setUsePrefix(true);
//        cacheManager.setCachePrefix(cacheName -> ("cache:" + cacheName).getBytes());
//        return cacheManager;
//    }
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheManager cacheManager = RedisCacheManager.create(factory);
        return cacheManager;
    }


    /**
     * 缓存注解的RedisTemplate
     * Jackson2JsonRedisSerializer来反序列化value值
     * StringRedisSerializer来序列化的key值
     */
    @Bean("cacheRedisTemplate")
    public RedisTemplate<Object, Object> cacheRedisTemplate() {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(om);
        template.setValueSerializer(serializer);

        //使用StringRedisSerializer来序列化的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();

        return template;
    }

    /**
     * StringRedisSerializer来序列化和反序列化的RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer keySerializer = new StringRedisSerializer();
        // RedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        // key采用字符串反序列化对象
        redisTemplate.setKeySerializer(keySerializer);
        //value也采用字符串反序列化对象
        //原因：管道操作，是对redis命令的批量操作，各个命令返回结果可能类型不同
        //可能是 Boolean类型 可能是String类型 可能是byte[]类型 因此统一将结果按照String处理
        redisTemplate.setValueSerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(keySerializer);
        return redisTemplate;
    }

    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
