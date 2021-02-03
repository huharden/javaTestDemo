package com.hj.study.test;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/*
 * @author: lijipeng
 * @date: 2021-01-29 16:14
 * @description: 提供map的四种遍历
 */
@Slf4j
public class MapErgodicModeTest {
    public static void main(String[] args) {

        log.info("------------------------开始方法一测试！--------------------------");
        /**
         * 方法一：在for循环中使用entries实现Map的遍历
         * 最常见也是大多数情况下用的最多的，一般在键值对都需要使用
         */
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("熊大", "棕色");
        map1.put("熊二", "黄色");
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            log.info(mapKey + ":" + mapValue);
        }

        log.info("------------------------开始方法二测试！--------------------------");
        /**
         * 方法二：在for循环中遍历key或者values，一般适用于只需要map中的key或者value时使用，在性能上比使用entrySet较好；
         *
         */
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("熊大", "棕色");
        map2.put("熊二", "黄色");
        //key
        for (String key : map2.keySet()) {
            log.info(key);

        }
        //value
        for (String value : map2.values()) {
            log.info(value);
        }

        log.info("------------------------开始方法三测试！--------------------------");
        /**
         * 方法三：通过Iterator遍历；
         *
         */
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("熊大", "棕色");
        map3.put("熊二", "黄色");
        Iterator<Map.Entry<String, String>> entries = map3.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            String key = entry.getKey();
            String value = entry.getValue();
            log.info(key + ":" + value);
        }

        log.info("------------------------开始方法四测试！--------------------------");
        /**
         * 方法四：通过键找值遍历，这种方式的效率比较低，因为本身从键取值是耗时的操作；
         *
         */
        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("熊大", "棕色");
        map4.put("熊二", "黄色");
        for (String key : map4.keySet()) {
            String value = map4.get(key);
            log.info(key + ":" + value);
        }

        log.info("------------------------验证HashMap使用！--------------------------");

        Map<String, String> map5 = new HashMap<>(3);



    }

}
