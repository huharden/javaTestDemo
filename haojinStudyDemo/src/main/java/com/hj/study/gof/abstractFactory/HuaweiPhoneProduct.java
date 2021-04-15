package com.hj.study.gof.abstractFactory;

/**
 * @author hutao
 * @className XiaomiPhoneProduct
 * @description TODO
 * @date 2021/4/15 9:48 上午
 */
public class HuaweiPhoneProduct implements IPhoneProduct{

    @Override
    public void start() {
        System.out.println("华为手机开机");
    }

    @Override
    public void shutdown() {
        System.out.println("华为手机关机");
    }

    @Override
    public void call() {
        System.out.println("华为手机打电话");
    }

    @Override
    public void message() {
        System.out.println("华为手机发消息");
    }
}
