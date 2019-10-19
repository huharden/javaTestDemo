package com.brock.smootbursty.car;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-08-16-10:58
 */
public class Car {
    public Car(){
        System.out.println("构造器===>>>cat....");
    }

    private String Brand;


    public void setBrand(String brand) {
        System.out.println("为属性赋值===>>>This brand id "+ brand);
        Brand = brand;
    }

    public void init(){
        System.out.println("初始化方法===>>>init ....");
    }

    public void destroy(){
        System.out.println("销毁方法===>>>destroy ....");
    }

}
