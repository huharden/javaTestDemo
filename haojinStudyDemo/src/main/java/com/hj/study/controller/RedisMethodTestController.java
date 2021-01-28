package com.hj.study.controller;

import com.hj.study.constant.CacheConstants;
import com.hj.study.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName：javaTestDemo
 * @className：KafkaSendcontroller
 * @describe：TODO
 * @createTime：2020/4/13 5:04 下午
 * @author：HuTao
 */
@RestController
@RequestMapping("/redis")
public class RedisMethodTestController {

    @Autowired
    private CacheUtil cacheUtil;

    private static final String CONSUME_FAILED = "consume_failed";

    @RequestMapping(value = "/method")
    public void send() {
        List<String> failedList = new ArrayList<>();
        failedList.add("abcdefg1121");
        failedList.add("abcdefg1122");
        failedList.add("abcdefg1123");
        failedList.add("abcdefg1124");
        failedList.add("abcdefg1125");

        String key = "20210126" + CONSUME_FAILED;

        for (String faileValue : failedList) {
            cacheUtil.push(CacheConstants.TEST_REDIS, key, faileValue);
        }

        long size1 = cacheUtil.size(CacheConstants.TEST_REDIS, key);
        System.out.println("所有元素长度是1------------：" + size1);

        List<String> range = cacheUtil.range(CacheConstants.TEST_REDIS, key, 0, -1);
        for (String str1 : range) {
            System.out.println("所有元素是1------------：" + str1);
        }

        List<String> sangeList = cacheUtil.range(CacheConstants.TEST_REDIS, key, 0, 2);
        for (String str2 : sangeList) {
            System.out.println("0到2元素是--------：" + str2);
        }
        // 追加异常信息进 txt 文件
        //ftpUtil.fileChaseFWNewLine(file.getPath(), allFaileList);
        //追加完异常信息后删除缓存中0-100的数据，101到后面的缓存数据会被保留
        cacheUtil.trim(CacheConstants.TEST_REDIS, key, 3, -1);

        long size2 = cacheUtil.size(CacheConstants.TEST_REDIS, key);
        System.out.println("所有元素长度是2------------：" + size2);

        List<String> range2 = cacheUtil.range(CacheConstants.TEST_REDIS, key, 0, -1);
        for (String str3 : range2) {
            System.out.println("所有元素是2------------：" + str3);
        }

    }

}