package com.hj.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @projectName：javaTestDemo
 * @className：JedisClusterConfig
 * @describe：TODO
 * @createTime：2020/4/28 9:43 上午
 * @author：HuTao
 */
//@Configuration
public class JedisClusterConfig {

    @Autowired
    private RedisProperties redisProperties;

    public JedisCluster getJedisCluster(){
        String [] serverArray=redisProperties.getClusterNodes().split(",");
        Set<HostAndPort> nodes=new HashSet<>();

        for (String ipPort:serverArray){
            String [] ipPortPair=ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));

        }
        return  new JedisCluster(nodes,redisProperties.getCommandTimeout());
    }
}
