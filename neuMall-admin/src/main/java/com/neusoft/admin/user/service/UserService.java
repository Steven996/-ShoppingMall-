package com.neusoft.admin.user.service;

import com.neusoft.admin.user.entity.UserBatchVO;
import com.neusoft.admin.user.entity.UserInfo;
import com.neusoft.admin.user.entity.UserInfoVO;
import com.neusoft.common.response.AppResponse;

import java.io.UnsupportedEncodingException;

public interface UserService {
    AppResponse saveUser(UserInfo userInfo) throws UnsupportedEncodingException;

    AppResponse listUsers(UserInfoVO userInfo);

    AppResponse deleteUser(UserBatchVO userInfo);

    AppResponse updatePwd(UserInfo userInfo) throws UnsupportedEncodingException;

    AppResponse updateUser(UserInfo userInfo);

    UserInfo getUserById(String userUuid);
}
