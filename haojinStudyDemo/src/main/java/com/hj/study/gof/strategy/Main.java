package com.hj.study.gof.strategy;

import com.hj.study.utils.JsonUtil;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 7, 6};
        Sorter.sort(arr);
        System.out.println(JsonUtil.toJson(arr));

    }
}
