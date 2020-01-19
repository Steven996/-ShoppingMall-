package com.neusoft.mall.customer.controller;

import com.neusoft.common.response.AppResponse;

import com.neusoft.common.util.CreateMD5;
import com.neusoft.common.util.UUIDUtil;
import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.customer.service.AccountUserService;
import com.neusoft.mall.customer.service.impl.AccountUserServiceImpl;
import com.neusoft.mall.customer.service.impl.CustomerServiceImpl;
import com.neusoft.mall.util.RedisConfig;
import com.neusoft.mall.util.StringLengthEmptyUndefind;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName: CustomerPassWordController
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/9
 */
@RestController
@Slf4j
@RequestMapping("front/account")
@Api
public class AccountUserController {
    @Autowired
    private AccountUserServiceImpl accountUserService;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("registered")
    public AppResponse registered(Customer customer) throws UnsupportedEncodingException {

        String customerNumber=customer.getCustomerNumber(),customerPassword=customer.getCustomerPassword(),customerName=customer.getCustomerName();

        int resultInt=accountUserService.registeredService(customerNumber,customerPassword,customerName);

        //System.out.println("front/account/registered");
        //System.out.println("cNumber:"+customerNumber+" cpw:"+customerPassword+" cuName:"+customerName);

        if(resultInt==-2){
            return AppResponse.bizError("您的用户名、账号、密码不符合要求\n(要求：非空，非undefind)");
        }else if(resultInt>100){
            return AppResponse.bizError("账号已存在");
        }else if(resultInt==1){
            return AppResponse.success("注册成功");
        }
        return AppResponse.bizError("未知错误");
    }


    @PostMapping("userLogin")
    public AppResponse userLogin(Customer customer) throws IOException {
        String customerNumber=customer.getCustomerNumber(),customerPassword=customer.getCustomerPassword();

        int resultInt = accountUserService.userLoginService(customerNumber,customerPassword);

        //System.out.println("front/account/userLogin");
        //System.out.println("nb:"+customerNumber+" ps:"+customerPassword+" resultInt:"+resultInt);

        if(resultInt==-3){
            //System.out.println("服务器被攻击");
            //log.info("服务器被攻击");
        }
        if(resultInt==1){
            Customer customerV0 = customerService.getCustomerByNumber(customerNumber);
            String tokenFront =UUIDUtil.uuidStr();
            List list = new ArrayList<String>();
            list.add(tokenFront);
            list.add(customerV0.getCustomerId());
            redisTemplate.opsForValue().set(tokenFront,customerV0,30,TimeUnit.MINUTES);
            return AppResponse.success("登录成功",list);
        }else if(resultInt==-2){
            return AppResponse.bizError("用户账号、密码为空");
        }else if(resultInt==-1 || resultInt==0){
            return AppResponse.bizError("用户账号、密码错误");
        }
        return AppResponse.bizError("未知错误");
    }

    @PutMapping("updatePassWord")
    public AppResponse updatePassWord(Customer customer) throws UnsupportedEncodingException {

        Customer customerOwn=(Customer)redisTemplate.opsForValue().get(customer.getTokenFront());
        String customerId=customerOwn.getCustomerId(),customerPassword=customer.getCustomerPassword() ,
                customerNewPassword=customer.getCustomerNewPassword();


        int resultInt = accountUserService.updatePasswordService(customerId,customerPassword,customerNewPassword);

        //System.out.println("front/account/updatePassWord");
        //System.out.println("id :"+customerId+" ps:"+customerPassword+" newps:"+customerNewPassword+" resultInt:"+resultInt);

        if(resultInt==-2){
            return AppResponse.bizError("账号、原密码、新密码为空");
        }else if(resultInt==-1){
            return AppResponse.bizError("原密码错误");
        }else if(resultInt==1){
            return AppResponse.success("修改成功");
        }else if(resultInt==0){
            return AppResponse.bizError("修改失败");
        }

        return AppResponse.bizError("未知错误");
    }







}
