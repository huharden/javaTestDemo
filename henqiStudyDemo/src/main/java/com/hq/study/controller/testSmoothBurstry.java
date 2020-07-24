package com.hq.study.controller;

import com.hq.study.utils.RedisUtils;
import com.sun.istack.internal.Nullable;
import com.vdurmont.emoji.EmojiParser;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Brock.lee
 * @program: ali-pay-test
 * @description: 平滑限流测试
 * @email 13420130887@163.com
 * @date 2019/6/13 10:56
 **/
@RestController
@RequestMapping("/test")
public class testSmoothBurstry {
    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;
    private Map<String, Object> map;

    @RequestMapping("/test1")
    @ResponseBody
    public String test1() {
        // netty异步请求

        System.out.println("888888888888");

        return "123";
    }
    /*突发示例*/
    @PostMapping("/test2")
    public void test2(@RequestBody Map<String, String> map) {

        String question = map.get("question");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        System.out.println("question===>>>" + question);
        String newQuestion = EmojiParser.removeAllEmojis(question);
        System.out.println("newQuestion===>>>" + newQuestion);
        //调用33实验室侧提供的获取智能回答的接口
        String aiMsgUrl = "http://10.0.98.235/search?question="+ newQuestion + "&app_code=1&size=3";
        String url = "http://10.0.98.235/search";
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("question", question);
        requestMap.put("app_code", "1");
        requestMap.put("size", "3");
        //requestMap.put("Content-Type", "application/json");

        try {
            String result = "ces";
            //String result = HttpClientUtil.get(aiMsgUrl, headers);
            System.out.println(result);
            JSONObject jsonobject = JSONObject.fromObject(result);
            System.out.println(jsonobject);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/test3")
    public void test3(){


        for(int i=0; i<100; i++){
            UUID topic_id = UUID.randomUUID();
            redisUtils.set("topicesAIEnd:" + topic_id.toString(), "测试id: "+ topic_id.toString(), 20 );

        }
        System.out.println("成功");
    }

    @PostMapping("/test4")
    public Long getTimeConsuming1() throws Exception {

        Long now = System.currentTimeMillis();
        Random x = new Random(10);
        try {
            for(int i=0; i<100000; i++){

                redisUtils.hPut("2101211994"+ x.nextInt(), "121212", i+ "测试1112");
            }

            Long time = System.currentTimeMillis()-now;
            System.out.println("发送成功,耗时===>>>" + time);
            return time;
        }catch (Exception e){
            throw new Exception("系统异常");

        }
    }

    /**
     * 使用redis管道进行数据存储
     * @throws Exception
     */
    @PostMapping("/test5")
    public Long getTimeConsuming2() {

        Long now = System.currentTimeMillis();
        Random x = new Random(100);
        // 使用redis 管道进行数据存储
        SessionCallback sessionCallback = new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations)
                    throws DataAccessException {
                operations.multi();
                for(int i = 0; i<100000; i++){
                    operations.opsForHash().put("1101211994"+ x.nextInt(), "121212", i+ "测试1112");
                }
                return operations.exec();
            }
        };
        redisTemplate.executePipelined(sessionCallback);

        Long time = System.currentTimeMillis()-now;
        System.out.println("发送成功,耗时===>>>" + time);
        return time;
    }


    /**
     * 使用redis管道进行数据存储
     * @throws Exception
     */
    @PostMapping("/test7")
    public void test7(){
        Long now = System.currentTimeMillis();
        Random x = new Random(100);
        List<Long> list = redisTemplate.executePipelined(new RedisCallback<Long>() {
            @Nullable
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (int i = 0; i < 100000; i++) {
                    String key = "123" + i;
                    connection.zCount(key.getBytes(), 0,Integer.MAX_VALUE);
                }
                connection.closePipeline();
                return null;

            }
        });
        Long time = System.currentTimeMillis()-now;
        System.out.println("发送成功,耗时===>>>" + time);
    }

}
