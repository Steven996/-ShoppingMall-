package com.neusoft.admin.accountAdmin.controller;

import com.neusoft.admin.accountAdmin.entity.SysUser;
import com.neusoft.admin.accountAdmin.service.AccountAdminService;
import com.neusoft.admin.util.StringUtil;
import com.neusoft.common.response.AppResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: AccountAdminController
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/20
 */

@RestController
@Slf4j
@RequestMapping("/backend/login")
@Api

public class AccountAdminController {
    @Resource
    private AccountAdminService accountAdminService;
    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping("userLogin")
    public AppResponse userLogin(SysUser sysUser) throws UnsupportedEncodingException {
        String userAccount = sysUser.getUserAccount();
        String userPwd = sysUser.getUserPwd();
        int resultInt=accountAdminService.userLogin(userAccount,userPwd);
        if(resultInt==1){
            String tokenBackend = StringUtil.getUUID();
            SysUser sysUser1 = accountAdminService.getSysUser(userAccount);
            redisTemplate.opsForValue().set(tokenBackend,sysUser1,30, TimeUnit.MINUTES);
            return AppResponse.success("登录成功",tokenBackend);
        }
        return AppResponse.bizError("登录失败");
    }

}
