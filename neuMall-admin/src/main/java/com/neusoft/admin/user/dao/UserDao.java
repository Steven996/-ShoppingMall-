package com.neusoft.admin.user.dao;

import com.neusoft.admin.user.entity.UserBatchVO;
import com.neusoft.admin.user.entity.UserInfo;
import com.neusoft.admin.user.entity.UserInfoVO;

import java.util.List;

public interface UserDao {
    /**
     * @Dept：南京软件研发中心
     * @Description：统计用户账号数量
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：int
     */
    int countUserAcct(UserInfo userInfo);

    /**
     * @Dept：南京软件研发中心
     * @Description：新增用户
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：int
     */
    int saveUser(UserInfo userInfo);

    /**
     * @Dept：南京软件研发中心
     * @Description：获取所有用户信息
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：int
     */
    List<UserInfo> listUsers(UserInfoVO userInfo);

    /**
     * @Dept：南京软件研发中心
     * @Description：删除用户
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：int
     */
    int deleteUser(UserBatchVO userInfo);

    /**
     * @Dept：南京软件研发中心
     * @Description：根据uuid获取用户信息
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userUuid
     * @Return：com.neusoft.admin.user.entity.UserInfo
     */
    UserInfo getUserById(String userUuid);

    /**
     * @Dept：南京软件研发中心
     * @Description：修改密码
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：int
     */
    int updateUserPwd(UserInfo userInfo);
    /**
     * @Dept：南京软件研发中心
     * @Description：修改用户
     * @Author：shengtt
     * @Date: 2019/4/3
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    int updateUser(UserInfo userInfo);
}
