package com.hj.study.gof.abstractFactory;

/**
 * @author hutao
 * @className IPhoneProduct
 * @description 抽象
 * @date 2021/4/15 9:43 上午
 */
public interface IPhoneProduct {
    void start();
    void shutdown();
    void call();
    void message();
}
