package com.hj.study.gof.builder;

/**
 * @author hutao
 * @className Worker
 * @description TODO
 * @date 2021/4/15 10:34 上午
 */
public class Worker extends Builder{

    private Product product;

    public Worker() {
        this.product = new Product();
    }

    @Override
    Builder builderA(String msg) {
        product.setBuilderA(msg);
        return this;
    }

    @Override
    Builder builderB(String msg) {
        product.setBuilderB(msg);
        return this;
    }

    @Override
    Builder builderC(String msg) {
        product.setBuilderC(msg);
        return this;
    }

    @Override
    Builder builderD(String msg) {
        product.setBuilderD(msg);
        return this;
    }

    @Override
    Product getProduct() {
        return this.product;
    }
}
