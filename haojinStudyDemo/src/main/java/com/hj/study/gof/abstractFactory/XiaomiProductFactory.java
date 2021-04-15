package com.hj.study.gof.abstractFactory;

/**
 * @author hutao
 * @className XiaomiProductFactory
 * @description 小米产品工厂
 * @date 2021/4/15 10:04 上午
 */
public class XiaomiProductFactory implements IProductFactory{

    @Override
    public IPhoneProduct phoneProduct() {
        return new XiaomiPhoneProduct();
    }

    @Override
    public IRouterProduct routerProduct() {
        return new XiaomiRouterProduct();
    }
}
