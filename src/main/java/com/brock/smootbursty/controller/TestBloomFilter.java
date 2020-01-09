package com.brock.smootbursty.controller;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class TestBloomFilter {
    public  static int total = 1000000;

    public static BloomFilter bf = BloomFilter.create(Funnels.integerFunnel(), total);

    public static void main(String[] args) {
        for(int i=0; i<total; i++){
            bf.put(i);
        }

        for (int i =0; i<total; i++){
            if(!bf.mightContain(i)){
                System.out.println("过滤器逃脱===>>>" + i);
            }
        }

        // 匹配不在过滤器中的10000个值，有多少匹配出来
        int count = 0;
        for (int i = total; i < total + 10000; i++) {
            if (bf.mightContain(i)) {
                count++;
            }
        }
        System.out.println("误伤的数量：" + count);

    }
}
