package com.brock.smootbursty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-06-17-20:50
 */
@Configuration
public class CommonConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }

}
