package com.hj.study.thread.waitNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hutao
 * @className MyList
 * @description TODO
 * @date 2021/6/23 2:30 下午
 */
public class MyList {

    static List<String> list = new ArrayList<>();

    public static void add(){
        list.add("a");
    }

    public static int size(){
        return list.size();
    }
}
