package com.neusoft.admin.accountAdmin.service.impl;

import com.neusoft.admin.accountAdmin.dao.AccountAdminDao;
import com.neusoft.admin.accountAdmin.entity.SysUser;
import com.neusoft.admin.accountAdmin.service.AccountAdminService;
import com.neusoft.admin.util.StringLengthEmptyUndefind;
import com.neusoft.common.util.CreateMD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName: AccountAdminServiceImpl
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/22
 */

@Service
public class AccountAdminServiceImpl implements AccountAdminService {
    @Resource
    StringLengthEmptyUndefind stringLengthEmptyUndefind;

    @Resource
    AccountAdminDao accountAdminDao;
    //-3:服务器被攻击，-2:用户名或密码不符合要求,0:登录失败，1:登录成功,ther：未知
    @Override
    public int userLogin(String userAccount, String userPwd) throws UnsupportedEncodingException {
        System.out.print("\nservice:"+userAccount+" pwd:"+userPwd+" ");
        if(!stringLengthEmptyUndefind.pd(new String[]{userAccount,userPwd},1,true)){
            return -2;
        }
        System.out.println("service00:"+userAccount+" pwd:"+userPwd+" ");
        userAccount=userAccount.trim();
        userPwd= CreateMD5.getMd5(userPwd.trim());
        System.out.println("userAccount:"+userAccount+" userPwd:"+userPwd);
        int res=accountAdminDao.userLogin(userAccount,userPwd);
        System.out.println("res"+res);
        if(res==1){
            if(userPwd.equals(accountAdminDao.finduserPwd(userAccount))){
                return 1;
            }
            return -3;
        }
        return res;
    }

    @Override
    public SysUser getSysUser(String userAccount) {
        return accountAdminDao.getSysUser(userAccount);
    }
}