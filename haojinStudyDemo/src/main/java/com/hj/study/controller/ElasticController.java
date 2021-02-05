package com.hj.study.controller;

import com.hj.study.service.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hutao
 * @className ElasticController
 * @description TODO
 * @date 2021/2/1 2:44 下午
 */
@RestController
public class ElasticController {

    @Autowired
    private ElasticService service;

    @RequestMapping(value = "/esTest",method = RequestMethod.GET)
    public boolean test() throws IOException {
        Date d = new Date();
        String id = d.getTime()+"";
        Map<String, Object> m = new HashMap<>();
        m.put("id", id);
        m.put("area_id", 1);
        m.put("camera_id", 1);
        m.put("log_time","2021-02-01 11:11:11");
        m.put("age", 1);
        service.saveData("gateway_test_0201",id,m);
        return true;
    }
}
