package com.neusoft.mall.customer.dao;


import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.customer.entity.CustomerVo;

import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomerList(CustomerVo customerVo);
    List<Customer> getCustomerDetail(Customer customer);
    int addCustomer(Customer customer);
    List<Customer> IfExistCustomerNumber(Customer customer);
    int updateAccount(Customer customer);
    int updateAccountByStatus(Customer customer);
    int addVersion(Customer customer);
    Customer getCustomerByNumber(String customerNumber);
}
