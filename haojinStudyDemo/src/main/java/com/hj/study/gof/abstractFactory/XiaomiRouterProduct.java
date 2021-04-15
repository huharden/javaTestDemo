package com.hj.study.gof.abstractFactory;

/**
 * @author hutao
 * @className XiaomiRouterProduct
 * @description TODO
 * @date 2021/4/15 9:51 上午
 */
public class XiaomiRouterProduct implements IRouterProduct{

    @Override
    public void start() {
        System.out.println("小米路由器开机");
    }

    @Override
    public void shutdown() {
        System.out.println("小米路由器关机");
    }

    @Override
    public void setting() {
        System.out.println("小米路由器设置");
    }

    @Override
    public void connect() {
        System.out.println("小米路由器连接成功");
    }
}
