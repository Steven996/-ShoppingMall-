package com.neusoft.mall.customer.service.impl;

import com.neusoft.common.util.CreateMD5;
import com.neusoft.common.util.UUIDUtil;
import com.neusoft.mall.customer.dao.AccountUserDao;

import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.customer.service.AccountUserService;
import com.neusoft.mall.util.StringLengthEmptyUndefind;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @ClassName:
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/11
 */


/*
用户登录： 0:失败，1.成功
findUser(String customerNumber, String customerPassword)

账户注册： -2:用户名、账号、密码长度不符合要求或undefined，1：成功,>100:用户已存在，其他int：未知错误
 registeredService(String customerNumber, String customerPassword, String customerName)

修改密码：  -2:用户id、原密码、新密码为空，-1:原密码错误，1：成功，其他int：未知错误
updatePassWord(String customerId, String customerPassword, String customerNewPassword)
 */

@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class AccountUserServiceImpl implements AccountUserService {


    @Resource
    StringLengthEmptyUndefind stringLengthEmptyUndefind1;

    @Resource
    private AccountUserDao accountUserDao;


    //账户注册： -2:用户名、账号、密码长度不符合要求或undefined，1：成功,>100:用户已存在，其他int：未知错误
    @Override
    public int registeredService(String customerNumber, String customerPassword, String customerName) throws UnsupportedEncodingException {

        if(stringLengthEmptyUndefind1.pd(new String[]{customerNumber,customerPassword,customerName},0,false)){
            return -2;
        }
        int a = accountUserDao.findUserNumber(customerNumber);
        if(a!=0){
            return a+100;
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        Customer customer=new Customer();
        customer.setCustomerNumber(customerNumber);
        customer.setCustomerPassword(CreateMD5.getMd5(customerPassword));
        customer.setCustomerName(customerName);
        customer.setCustomerId(uuid);
        customer.setCreatedBy("system");
        customer.setLastModifiedBy("system");
        int b=accountUserDao.addUser(customer);

        return b;
    }

    //用户登录：-3:服务器可能正在被攻击，-2用户账号、密码为空，-1：账号、密码错误，1.成功,other:未知错误
    @Override
    public int userLoginService(String customerNumber, String customerPassword) throws UnsupportedEncodingException {

        if(stringLengthEmptyUndefind1.pd(new String[]{customerNumber,customerPassword},0,false)){
            return -2;
        }
        customerPassword=CreateMD5.getMd5(customerPassword);
        if(accountUserDao.findUser(customerNumber,customerPassword)==1){
            String str = accountUserDao.findUserPassWord(customerNumber);
            if(str.equals(customerPassword)){
                return accountUserDao.findUser(customerNumber,customerPassword);
            }
            return -3;
        }
        return -1;
    }

    //-2:用户id、原密码、新密码为空，-1:原密码错误，0：修改失败，1：成功，其他int：未知错误
    @Override
    public int updatePasswordService(String customerId, String customerPassword, String customerNewPassword) throws UnsupportedEncodingException {

        if(stringLengthEmptyUndefind1.pd(new String[]{customerId,customerPassword,customerNewPassword},0,false)){
            return -2;
        }
        customerPassword=CreateMD5.getMd5(customerPassword);
        customerNewPassword=CreateMD5.getMd5(customerNewPassword);
        if(accountUserDao.findUserId(customerId,customerPassword)==1){
            return accountUserDao.updatePassWord(customerId,customerNewPassword);
        }
        return -1;
    }




}
