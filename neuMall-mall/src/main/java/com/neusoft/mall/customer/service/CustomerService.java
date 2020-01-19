package com.neusoft.mall.customer.service;

import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.customer.entity.CustomerVo;



public interface CustomerService {
    AppResponse getCustomerList(CustomerVo customerVo);
    AppResponse getCustomerDetail(Customer customer);
    AppResponse addCustomer(Customer customer);
    AppResponse updateAccount(Customer customer);
    AppResponse updateAccountByStatus(Customer customer);
    Customer getCustomerByNumber(String customerNumber);
}
