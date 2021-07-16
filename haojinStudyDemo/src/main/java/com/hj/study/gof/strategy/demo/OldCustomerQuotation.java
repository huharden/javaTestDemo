package com.hj.study.gof.strategy.demo;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 老客户九折
 */
@Service
public class OldCustomerQuotation implements ICustomerQuotation{

    @Override
    public BigDecimal getQuotation(BigDecimal originalPrice) {
        System.out.println("这是一个Old用户");
        return originalPrice.multiply(new BigDecimal(0.9)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String getCustomerType() {
        return Constant.OLD;
    }

    @Override
    public BigDecimal getShippingFee() {
        return new BigDecimal(10.11);
    }
}
