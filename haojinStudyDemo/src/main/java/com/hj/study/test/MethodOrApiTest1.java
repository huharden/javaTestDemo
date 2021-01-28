package com.hj.study.test;

import com.hj.study.constant.CacheConstants;
import com.hj.study.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController

public class MethodOrApiTest1 {
    @Autowired
    private CacheUtil cacheUtil;
    private static final String CONSUME_FAILED = "consume_failed";

    public static void main(String[] args) throws ParseException {
        String fromDay = "20210101";
        String toDay = "20210119";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date fromDate = dateFormat.parse(fromDay);
        Date toDate = dateFormat.parse(toDay);

        Calendar baseCalendar = Calendar.getInstance();
        baseCalendar.setTime(fromDate);
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(fromDate);
        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(toDate);

        boolean equals = fromCalendar.equals(toCalendar);
        System.out.println("kkkkkkkkk11:" + equals);

        boolean before = fromCalendar.before(toCalendar);
        System.out.println("kkkkkkkkk22:" + before);

        int dayOfYear = Calendar.DAY_OF_YEAR;
        System.out.println("kkkkkkkkk33:" + dayOfYear);

        int i = fromCalendar.get(Calendar.DAY_OF_YEAR);
        System.out.println("kkkkkkkkk44:" + i);

        baseCalendar.set(Calendar.DAY_OF_YEAR, fromCalendar.get(Calendar.DAY_OF_YEAR));
        int i1 = baseCalendar.get(Calendar.DAY_OF_YEAR);
        System.out.println("kkkkkkkkk55:" + i1);

        Date time1 = baseCalendar.getTime();
        System.out.println("kkkkkkkkk66:" + time1);
        String format = dateFormat.format(time1);
        System.out.println("kkkkkkkkk77:" + format);


        //------------------------------------------------------
        String ab = "abcdefg";

        boolean contains = "abcdefghijk".contains(ab);
        System.out.println(contains);

        //----------------------------------------------------------




    }

    @GetMapping("/test")
    public void test2(){
        List<String> failedList = new ArrayList<>();
        failedList.add("abcdefg1121");
        failedList.add("abcdefg1122");
        failedList.add("abcdefg1123");
        failedList.add("abcdefg1124");
        failedList.add("abcdefg1125");

        String key = "20210126" + CONSUME_FAILED;

        for (String faileValue : failedList) {
            cacheUtil.push(CacheConstants.QUEUE_CONSUME_FAILRECORD, key, faileValue);
        }

        long size1 = cacheUtil.size(CacheConstants.QUEUE_CONSUME_FAILRECORD, key);
        System.out.println("所有元素长度是1------------：" + size1);

        List<String> range = cacheUtil.range(CacheConstants.QUEUE_CONSUME_FAILRECORD, key, 0, -1);
        for (String str1 : range) {
            System.out.println("所有元素是1------------：" + str1);
        }

        List<String> sangeList = cacheUtil.range(CacheConstants.QUEUE_CONSUME_FAILRECORD, key, 0, 2);
        for (String str2 : sangeList) {
            System.out.println("0到2元素是--------：" + str2);
        }
        // 追加异常信息进 txt 文件
        //ftpUtil.fileChaseFWNewLine(file.getPath(), allFaileList);
        //追加完异常信息后删除缓存中0-100的数据，101到后面的缓存数据会被保留
        cacheUtil.trim(CacheConstants.QUEUE_CONSUME_FAILRECORD, key, 3, -1);

        long size2 = cacheUtil.size(CacheConstants.QUEUE_CONSUME_FAILRECORD, key);
        System.out.println("所有元素长度是2------------：" + size2);

        List<String> range2 = cacheUtil.range(CacheConstants.QUEUE_CONSUME_FAILRECORD, key, 0, -1);
        for (String str3 : range2) {
            System.out.println("所有元素是2------------：" + str3);
        }

    }
}
