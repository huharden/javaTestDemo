package com.hj.study.gof.builder;

/**
 * @author hutao
 * @className Test
 * @description 单例模式
 * @date 2021/4/15 10:38 上午
 */
public class Test {

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.builderA("全家桶");
        worker.builderB("不好吃");
        System.out.println(worker.getProduct());
    }
}
