package com.hj.study.gof.strategy.demo;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NewCustomerQuotation implements ICustomerQuotation{

    @Override
    public BigDecimal getQuotation(BigDecimal originalPrice) {
        System.out.println("这是一个new用户");
        return originalPrice;
    }

    @Override
    public String getCustomerType() {
        return Constant.NEW;
    }

    @Override
    public BigDecimal getShippingFee() {
        return new BigDecimal(10.11);
    }
}
