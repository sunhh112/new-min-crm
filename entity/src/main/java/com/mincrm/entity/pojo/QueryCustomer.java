package com.mincrm.entity.pojo;

/**
 * Created by sunhh on 2019/12/21.
 */
public class QueryCustomer extends QueryPage {
    private String customerName ;
    private String customerTel ;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }
}
