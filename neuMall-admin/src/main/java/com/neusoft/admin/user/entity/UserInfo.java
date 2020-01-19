package com.neusoft.admin.user.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserInfo
 * @Description: 用户信息
 * @Author: shengtt
 * @Date: 2019/4/2
 */
@Data
public class UserInfo extends BaseEntity implements Serializable {

    private String userUuid;//用户代码
    private String userAccount;//用户账号
    private String userName;//用户姓名
    private String userPwd;//密码
    private String userNewPwd;//新密码
    private String userIdNumber;//身份证号码
    private String userTel;//手机号码
    private String userGender;//性别
    private String userIsUsed;//是否使用   1使用 0禁用
    private String userEmail;//电子邮件
    private String userComments;//备注
    private String tokenBackend;//管理员token

}
