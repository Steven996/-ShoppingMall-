package com.neusoft.mall.customer.dao;

import com.neusoft.mall.customer.entity.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: AccountUserDao
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/11
 */

public interface AccountUserDao {
    int updatePassWord(@Param("customerId") String customerId,@Param("customerPassword") String customerPassword);
    int findUser(@Param("customerNumber") String customerNumber, @Param("customerPassword") String customerPassword);
    int findUserId(@Param("customerId") String customerId, @Param("customerPassword") String customerPassword);
    int findUserNumber(@Param("customerNumber") String customerNumber);
    String findUserPassWord(@Param("customerNumber") String customerNumber);
    Customer findUserAll(@Param("customerNumber") String customerNumber);
    int addUser(Customer customer);
}
