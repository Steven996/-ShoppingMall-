package com.neusoft.admin.accountAdmin.service;

import com.neusoft.admin.accountAdmin.entity.SysUser;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: AccountAdminService
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/22
 */

public interface AccountAdminService {
    int userLogin(String userAccount,String userPwd) throws UnsupportedEncodingException;
    SysUser getSysUser(String userAccount);
}
