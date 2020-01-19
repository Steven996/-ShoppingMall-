package com.neusoft.admin.menu.controller;


import com.neusoft.admin.accountAdmin.entity.SysUser;
import com.neusoft.admin.menu.entity.Menu;
import com.neusoft.admin.menu.entity.MenuVO;
import com.neusoft.admin.menu.entity.Tree;
import com.neusoft.admin.menu.service.MenuService;
import com.neusoft.admin.user.entity.UserInfo;
import com.neusoft.common.response.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

import static java.awt.SystemColor.menu;

/**
 * @ClassName MenuService
 * @Description 菜单管理
 * @Author xywang
 * @Date 2019/4/8
 */
@RestController
@RequestMapping("admin/backend/menu")
@Validated
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private MenuService menuService;

    /**
     * 部门：南京软件研发中心
     * 功能：获取菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @RequestMapping(value = "/listMenus",method = RequestMethod.POST)
    public AppResponse listMenus(MenuVO menuVO) throws Exception {
            MenuVO menu=menuVO;
        try {
            Tree tree = menuService.listMenus();
            return AppResponse.success("查询成功", tree.getChildren());
        } catch (Exception e) {
            logger.error("菜单获取异常", e);
            throw new Exception("菜单获取失败，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @RequestMapping(value = "deleteMenu")
    public AppResponse deleteMenu(MenuVO menuVO) {
        try {
            //获取用户id
            SysUser backUserInfo = (SysUser) redisTemplate.opsForValue().get(menuVO.getTokenBackend());
            String userId = backUserInfo.getUserUuid();
            menuVO.setLastModifiedBy(userId);
            menuVO.setCreateBy(userId);
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuVO, menu);
            menuService.deleteMenu(menu);
            return AppResponse.success("菜单删除成功");
        } catch (Exception e) {
            logger.error("菜单删除错误", e);
            return AppResponse.notFound("删除失败");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：新增菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @RequestMapping(value = "insertMenu")
    public AppResponse insertMenu(@Valid MenuVO menuVO) {
        try {
            //获取用户id
            SysUser backUserInfo = (SysUser) redisTemplate.opsForValue().get(menuVO.getTokenBackend());
            String userId = backUserInfo.getUserUuid();
            menuVO.setLastModifiedBy(userId);
            menuVO.setCreateBy(userId);
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuVO, menu);
            return menuService.insertMenu(menu);
        } catch (Exception e) {
            logger.error("菜单新增异常", e);
            return AppResponse.notFound("新增失败");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @RequestMapping(value = "updateMenu")
    public AppResponse updateMenu(@Valid MenuVO menuVO) {
        try {
            //获取用户id
            SysUser backUserInfo = (SysUser) redisTemplate.opsForValue().get(menuVO.getTokenBackend());
            String userId = backUserInfo.getUserUuid();
            menuVO.setLastModifiedBy(userId);
            menuVO.setCreateBy(userId);
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuVO, menu);
            return menuService.updateMenu(menu);
        } catch (Exception e) {
            logger.error("菜单修改异常", e);
            return AppResponse.notFound("修改失败");
        }
    }

}
