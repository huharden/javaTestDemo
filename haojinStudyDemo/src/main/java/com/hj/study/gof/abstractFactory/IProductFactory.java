package com.hj.study.gof.abstractFactory;

/**
 * @author hutao
 * @className IPhone
 * @description 产品工厂
 * @date 2021/4/15 9:33 上午
 */
public interface IProductFactory {

    IPhoneProduct phoneProduct();

    IRouterProduct routerProduct();
}
