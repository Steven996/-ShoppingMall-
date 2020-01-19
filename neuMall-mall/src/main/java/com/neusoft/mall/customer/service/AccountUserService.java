package com.neusoft.mall.customer.service;

import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.customer.entity.Customer;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: AccountUserService
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/11
 */

public interface AccountUserService {
    int registeredService(String customerNumber,String customerPassword,String customerName) throws UnsupportedEncodingException;
    int updatePasswordService(String customerId,String customerPassword,String customerNewPassword) throws UnsupportedEncodingException;
    int userLoginService(String customerNumber, String customerPassword) throws UnsupportedEncodingException;
}
