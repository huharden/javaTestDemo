package com.hj.study.gof.strategy.demo;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface ICustomerQuotation {
    public BigDecimal getQuotation(BigDecimal originalPrice);

    String getCustomerType();

    /**
     * 邮费
     * @return
     */
    BigDecimal getShippingFee();

    public static Map<String, ICustomerQuotation> map = new ConcurrentHashMap();

    @PostConstruct
    default public void init() {
        map.put(getCustomerType(), this);
    }
}
