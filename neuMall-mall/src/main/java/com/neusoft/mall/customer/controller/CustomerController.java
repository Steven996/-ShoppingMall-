package com.neusoft.mall.customer.controller;


import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.customer.entity.CustomerVo;
import com.neusoft.mall.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName: CustomerController
 * @Description: 客户管理控制器层
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@RestController
@Slf4j
@RequestMapping("/backend/customer")
@Api("客户管理")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * @Dept：南京软件研发中心
     * @Description： 调service层 查询客户并分页
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 状态，账号，姓名，页码，条数
     * @Return： Map
     */
    @ApiOperation(value = "客户查询")
    @GetMapping(value = "/getCustomerList")
    public AppResponse getCustomerList(CustomerVo customerVo){
        return customerService.getCustomerList(customerVo);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调service层 查询客户详情
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 客户id
     * @Return： Map
     */
    @ApiOperation(value = "客户详情")
    @GetMapping(value = "/getCustomerDetail")
    public AppResponse getCustomerDetail(Customer customer){
        return customerService.getCustomerDetail(customer);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调service层 新增客户
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 客户账号，客户姓名，客户密码，是否使用
     * @Return： Map
     */
    @ApiOperation(value = "新增客户")
    @PostMapping(value = "/addCustomer")
    public AppResponse addCustomer(Customer customer){
        return customerService.addCustomer(customer);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调service层 客户修改
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 客户代码，客户姓名，版本号，是否使用
     * @Return： Map
     */
    @ApiOperation(value = "修改客户")
    @PutMapping(value = "/updateAccount")
    public AppResponse updateAccount(Customer customer){
        return customerService.updateAccount(customer);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调service层 客户启用/禁用
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 客户代码，是否使用
     * @Return： Map
     */
    @ApiOperation(value = "客户启用/禁用")
    @PutMapping(value = "/updateAccountByStatus")
    public AppResponse updateAccountByStatus(Customer customer){
        return customerService.updateAccountByStatus(customer);
    }
}
