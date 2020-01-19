package com.neusoft.admin.user.controller;

import com.neusoft.admin.accountAdmin.entity.SysUser;
import com.neusoft.admin.user.entity.UserBatchVO;
import com.neusoft.admin.user.entity.UserInfo;
import com.neusoft.admin.user.entity.UserInfoVO;
import com.neusoft.admin.user.service.UserService;
import com.neusoft.common.response.AppResponse;
import com.neusoft.common.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName: UserController
 * @Description:
 * @Author: shengtt
 * @Date: 2019/4/1
 */
@RestController
@RequestMapping("backend/user")
@Slf4j
@Api("用户管理")
public class UserController {

    @ApiOperation(value ="用户测试")
    @RequestMapping(value = "userHello",method = RequestMethod.GET)
    public AppResponse sayHello(){
        return AppResponse.success("hello user"+StringUtil.initNo());
    }
    /*public String sayHello(){
        return "hello user"+StringUtil.initNo();
    }*/
    @Resource
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * @Dept：南京软件研发中心
     * @Description：新增用户
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @PostMapping("saveUser")
    public AppResponse saveUser(@Valid UserInfo userInfo) throws Exception {
        try {
            //获取用户id
            SysUser backUserInfo = (SysUser) redisTemplate.opsForValue().get(userInfo.getTokenBackend());
            String userId = backUserInfo.getUserUuid();
            userInfo.setCreatedBy(userId);
            AppResponse appResponse = userService.saveUser(userInfo);
            return appResponse;
        } catch (Exception e) {
            log.error("用户新增失败", e);
            throw new Exception("用户新增失败，请重试");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：获取用户列表
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @GetMapping(value = "listUsers")
    public AppResponse listUsers(UserInfoVO userInfo) throws Exception {
        try {
            return userService.listUsers(userInfo);
        } catch (Exception e) {
            log.error("用户获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：删除用户信息
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @PutMapping("deleteUser")
    public AppResponse deleteUser(@RequestBody UserBatchVO userInfo) throws Exception {
        try {
            //获取用户id
            SysUser backUserInfo = (SysUser)redisTemplate.opsForValue().get(userInfo.getTokenBackend());
            String userId = backUserInfo.getUserUuid();
            userInfo.setLastModifiedBy(userId);
            return userService.deleteUser(userInfo);
        } catch (Exception e) {
            log.error("用户删除错误", e);
            throw new Exception("用户删除错误");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：修改密码
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @PostMapping("updatePwd")
    public AppResponse updatePwd(UserInfo userInfo) throws Exception {
        try {
            //获取用户id
            SysUser backUserInfo = (SysUser) redisTemplate.opsForValue().get(userInfo.getTokenBackend());
            String userId = backUserInfo.getUserUuid();
            userInfo.setLastModifiedBy(userId);
            return userService.updatePwd(userInfo);
        } catch (Exception e) {
            log.error("修改异常", e);
            throw new Exception("修改密码失败，请重试");
        }
    }
    /**
     * @Dept：南京软件研发中心
     * @Description：修改用户
     * @Author：shengtt
     * @Date: 2019/4/3
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @PutMapping("updateUser")
    public AppResponse updateUser(UserInfo userInfo) throws Exception {
        try {
            //获取用户id
            SysUser backUserInfo = (SysUser)redisTemplate.opsForValue().get(userInfo.getTokenBackend());
            String userId = backUserInfo.getUserUuid();
            userInfo.setLastModifiedBy(userId);
            return userService.updateUser(userInfo);
        } catch (Exception e) {
            log.error("修改用户信息错误", e);
            throw new Exception("系统错误，请重试");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：根据userUuid查询用户信息
     * @Author：shengtt
     * @Date: 2019/4/3
     * @Param：userUuid
     * @Return：com.neusoft.common.response.AppResponse
     */
    @GetMapping(value = "getUserByUserUuid/{userUuid}")
    public AppResponse getUserByUserCode(@PathVariable("userUuid") String userUuid,String tokenBackend) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userService.getUserById(userUuid);
        } catch (Exception e) {
            log.error("用户查询错误", e);
            throw new Exception("查询错误，请重试");
        }
        if (userInfo == null) {
            return AppResponse.notFound("无查询结果");
        }
        return AppResponse.success("查询成功", userInfo);
    }
}
