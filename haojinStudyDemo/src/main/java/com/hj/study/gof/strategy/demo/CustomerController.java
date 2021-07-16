package com.hj.study.gof.strategy.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CustomerController {
    public static final BigDecimal originalPrice = new BigDecimal(7100.00).setScale(2, BigDecimal.ROUND_HALF_UP);

    @GetMapping("/test")
    public String getPrice(String customerType){
        BigDecimal myPrice = originalPrice;
        CustomerQuotationContext customerQuotationContext = new CustomerQuotationContext(customerType);
        return customerQuotationContext.getPrice(myPrice);
    }
}
