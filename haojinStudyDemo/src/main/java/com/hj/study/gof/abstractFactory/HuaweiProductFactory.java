package com.hj.study.gof.abstractFactory;

/**
 * @author hutao
 * @className XiaomiProductFactory
 * @description 华为产品工厂
 * @date 2021/4/15 10:04 上午
 */
public class HuaweiProductFactory implements IProductFactory{

    @Override
    public IPhoneProduct phoneProduct() {
        return new HuaweiPhoneProduct();
    }

    @Override
    public IRouterProduct routerProduct() {
        return new HuaweiRouterProduct();
    }
}
