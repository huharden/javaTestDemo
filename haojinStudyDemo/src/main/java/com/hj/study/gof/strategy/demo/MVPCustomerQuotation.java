package com.hj.study.gof.strategy.demo;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * MVP用户7折
 */
@Service
public class MVPCustomerQuotation implements ICustomerQuotation{

    @Override
    public BigDecimal getQuotation(BigDecimal originalPrice) {
        System.out.println("这是一个MVP用户");
        return originalPrice.multiply(new BigDecimal(0.7)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String getCustomerType() {
        return Constant.MVP;
    }

    @Override
    public BigDecimal getShippingFee() {
        return new BigDecimal(0.11);
    }
}
