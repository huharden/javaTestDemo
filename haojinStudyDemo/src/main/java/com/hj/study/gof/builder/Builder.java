package com.hj.study.gof.builder;

/**
 * @author hutao
 * @className Builder
 * @description 建造者
 * @date 2021/4/15 10:25 上午
 */
public abstract class Builder {

    abstract Builder builderA(String msg);

    abstract Builder builderB(String msg);

    abstract Builder builderC(String msg);

    abstract Builder builderD(String msg);

    abstract Product getProduct();

}
