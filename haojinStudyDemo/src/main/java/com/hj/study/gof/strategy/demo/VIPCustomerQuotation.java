package com.hj.study.gof.strategy.demo;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * VIP用户8折
 */
@Service
public class VIPCustomerQuotation implements ICustomerQuotation{

    @Override
    public BigDecimal getQuotation(BigDecimal originalPrice) {
        System.out.println("这是一个VIP用户");
        return originalPrice.multiply(new BigDecimal(0.8)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String getCustomerType() {
        return Constant.VIP;
    }

    @Override
    public BigDecimal getShippingFee() {
        return new BigDecimal(9.11);
    }
}
