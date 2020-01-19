package com.neusoft.admin.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.admin.user.dao.UserDao;
import com.neusoft.admin.user.entity.UserBatchVO;
import com.neusoft.admin.user.entity.UserInfo;
import com.neusoft.admin.user.entity.UserInfoVO;
import com.neusoft.admin.user.service.UserService;
import com.neusoft.common.entity.PageVo;
import com.neusoft.common.response.AppResponse;
import com.neusoft.common.util.CreateMD5;
import com.neusoft.common.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户管理
 * @Author: shengtt
 * @Date: 2019/4/1
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * @Dept：南京软件研发中心
     * @Description：新增用户
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse saveUser(UserInfo userInfo) throws UnsupportedEncodingException {
        // 校验账号是否存在
        int countUserAcct = userDao.countUserAcct(userInfo);
        if (0 != countUserAcct) {
            return AppResponse.bizError("用户账号已存在，请重新输入！");
        }
        // 密码加密 默认为123456
        String pwd = CreateMD5.getMd5("123456");
        userInfo.setUserUuid(UUIDUtil.uuidStr());
        userInfo.setUserPwd(pwd);
        // 新增用户
        int count = userDao.saveUser(userInfo);
        if (0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：获取用户列表
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse listUsers(UserInfoVO userInfo) {
        //查询所有用户
        PageVo pageVo = new PageVo();
        PageHelper.startPage(userInfo.getPageNum(), userInfo.getPageSize());
        List<UserInfo> userInfoList = userDao.listUsers(userInfo);
        pageVo.setList(userInfoList);
        pageVo.setTotalRecords((int) new PageInfo<UserInfo>(userInfoList).getTotal());
        return AppResponse.success("查询成功！", pageVo);
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：删除用户
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse deleteUser(UserBatchVO userInfo) {
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除用户
        int count = userDao.deleteUser(userInfo);
        if (0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }
    /**
     * @Dept：南京软件研发中心
     * @Description：修改密码
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse updatePwd(UserInfo userInfo) throws UnsupportedEncodingException {
        AppResponse appResponse = AppResponse.success("修改密码成功！");
        // 需要校验原密码是否正确
        /*if (null != userInfo.getUserPwd() && !"".equals(userInfo.getUserPwd())) {
            String oldPwd = CreateMD5.getMd5(userInfo.getUserPwd());
            // 根据uuid获取用户信息
            UserInfo userDetail = userDao.getUserById(userInfo.getUserUuid());

            if (null == userDetail) {
                return AppResponse.bizError("用户不存在或已被删除！");
            } else {
                if (!oldPwd.equals(userDetail.getUserPwd())) {
                    return AppResponse.bizError("原密码不匹配，请重新输入！");
                }
            }
        }*/
        // 根据uuid获取用户信息
        UserInfo userDetail = userDao.getUserById(userInfo.getUserUuid());

        if (null == userDetail) {
            return AppResponse.bizError("用户不存在或已被删除！");
        }

        // 修改密码
        userInfo.setUserPwd(CreateMD5.getMd5(userInfo.getUserNewPwd()));
        int count = userDao.updateUserPwd(userInfo);
        if (0 == count) {
            appResponse = AppResponse.bizError("修改密码失败，请重试！");
        }
        return appResponse;
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：修改用户
     * @Author：shengtt
     * @Date: 2019/4/3
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse updateUser(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        // 修改用户信息
        int count = userDao.updateUser(userInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        // 新增用部门
        return appResponse;
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：根据userUuid查询用户信息
     * @Author：shengtt
     * @Date: 2019/4/3
     * @Param：userUuid
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public UserInfo getUserById(String userUuid) {
        return userDao.getUserById(userUuid);
    }
}
