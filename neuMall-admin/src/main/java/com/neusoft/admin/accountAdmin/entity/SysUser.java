package com.neusoft.admin.accountAdmin.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: SysUser
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/9
 */
@Data
public class SysUser extends BaseEntity { //数据库对应表：t_sys_user
    private String userUuid;
    private String userAccount;
    private String userName;
    private String userPwd;
    private String userIdNumber;
    private String userTel;
    private int userGender;
    private int userIsUsed;
    private String userEmail;
    private String userComments;
    private String tokenBackend;
}
