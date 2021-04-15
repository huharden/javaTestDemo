package com.hj.study.gof.abstractFactory;

/**
 * @author hutao
 * @className Test
 * @description TODO
 * @date 2021/4/15 10:07 上午
 */
public class Test {

    public static void main(String[] args) {
        //小米产品工厂
        IProductFactory xiaomiProduct = new XiaomiProductFactory();
        xiaomiProduct.phoneProduct().start();
        xiaomiProduct.routerProduct().start();

        //华为产品工厂
        IProductFactory huaweiProduct = new HuaweiProductFactory();
        huaweiProduct.phoneProduct().start();
        huaweiProduct.routerProduct().start();

    }
}
