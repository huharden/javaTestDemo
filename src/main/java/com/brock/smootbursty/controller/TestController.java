package com.brock.smootbursty.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-09-05-16:30
 */
public class TestController {

    public static void main(String[] args){
        float num= (float)2/2 ;
        //格式化小数
        DecimalFormat df = new DecimalFormat("0.00");
        //返回的是String类型
        Double rate = Double.valueOf(df.format(num));

        System.out.println(rate);
        System.out.println(df.format(num));

        Long aa = 59000L/1000;
        System.out.println(aa);
        Integer bb = Math.toIntExact(aa/60>=0.5?aa/60:aa/60+1);
        System.out.println(bb);

        Date date = new Date();
        //转换提日期输出格式

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = dateFormat.format(date);
        System.out.println("当前时间为：" + date1);


    }
}
