package com.hj.study.gof.strategy.demo;


import java.math.BigDecimal;

public class CustomerQuotationContext {

    private ICustomerQuotation quotation;
    public CustomerQuotationContext(String customerType) {
        ICustomerQuotation iCustomerQuotation = ICustomerQuotation.map.get(customerType);
        this.quotation = iCustomerQuotation;
    }

    public String getPrice(BigDecimal originalPrice) {
        System.out.println("进行一些前置处理");
        BigDecimal price = quotation.getQuotation(originalPrice);
        System.out.println("进行一些后置处理");
        return price.toPlainString();
    }
}
