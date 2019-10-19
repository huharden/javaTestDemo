package com.brock.smootbursty.car;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-08-16-11:30
 */
public class BeanProccess implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器===>>>" + "BeforeInitialization...." + bean + ","+ beanName);
        return bean;
     }

     @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
         System.out.println("后置处理器===>>>" + "AfterInitialization..." + bean + ","+ beanName);
        return bean;
    }



}
