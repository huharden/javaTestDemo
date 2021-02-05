package com.hj.study.controller;

import io.netty.handler.codec.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GatewayController {

    @PostMapping("/aiability/v1/ocr")
    public Map<String, Object> cmClassify(HttpServletRequest request){
        String XServerParam = request.getHeader("X-Server-Param");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("error_code", "0");
        resultMap.put("error","");

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("type", "1");
        dataMap.put("name", "工商aa执照");
        resultMap.put("data", dataMap);
        return resultMap;
    }
}
