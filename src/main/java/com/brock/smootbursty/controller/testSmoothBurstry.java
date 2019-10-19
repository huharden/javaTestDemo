package com.brock.smootbursty.controller;

import com.brock.smootbursty.utils.HttpClientUtil;
import com.brock.smootbursty.utils.RedisUtils;
import com.vdurmont.emoji.EmojiParser;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
            String result = HttpClientUtil.sendHttpPost(url, requestMap);
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
    public void test(HttpServletRequest request){
        String aa = request.getParameter("aa");
        System.out.println("接收成功");
    }
}
