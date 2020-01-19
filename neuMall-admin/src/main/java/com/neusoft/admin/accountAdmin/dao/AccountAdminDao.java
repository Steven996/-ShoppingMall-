package com.neusoft.admin.accountAdmin.dao;

import com.neusoft.admin.accountAdmin.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: AccountAdminDao
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/20
 */

public interface AccountAdminDao {
    int userLogin(@Param("userAccount") String userAccount, @Param("userPwd") String userPwd);
    String finduserPwd(@Param("userAccount") String userAccount);
    SysUser getSysUser(String userAccount);
}
