package com.hj.study.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName：javaTestDemo
 * @className：RedisConfig
 * @describe：TODO
 * @createTime：2020/4/28 9:23 上午
 * @author：HuTao
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis.cache")
public class RedisProperties {

    private int expireSeconds;

    private String clusterNodes;
    private int commandTimeout;

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public int getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

}
