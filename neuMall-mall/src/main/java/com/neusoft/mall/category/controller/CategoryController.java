package com.neusoft.mall.category.controller;



import com.neusoft.admin.accountAdmin.entity.SysUser;
import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.category.entity.Category;
import com.neusoft.mall.category.entity.Tree;
import com.neusoft.mall.category.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: CategoryController
 * @Description:分类管理
 * @Author: shm12q
 * @Date: 2019/4/8
 */
@RestController
@RequestMapping(value = "backend/category")
@Validated
@Api(value = "分类管理")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Resource
    CategoryService categoryService;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * @Dept：Neusoft
     * @Description：查询分类
     * @Author：shm12q
     * @Date: 2019/4/10
     * @Return：com.neusoft.common.response.AppResponse
     */
    @GetMapping("getCategory")
    @ApiOperation("获取分类")
    public AppResponse getCategory() throws Exception {
        try {
            Tree tree = categoryService.getCategory();
            return AppResponse.success("查询成功", tree.getChildren());
        } catch (Exception e) {
            logger.error("菜单获取异常", e);
            throw new Exception("分类获取失败，请重试");
        }
    }

    /**
     * @Dept：Neusoft
     * @Description：删除分类
     * @Author：shm12q
     * @Date: 2019/4/10
     * @Param：分类信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @PutMapping("deleteCategory")
    @ApiOperation("删除分类")
    public AppResponse deleteCategory(  Category category){
        try {
            //获取用户id
            SysUser sysUser = (SysUser)redisTemplate.opsForValue().get(category.getTokenBackend());
            String userId = sysUser.getUserUuid();
            category.setLastModifiedBy(userId);
            category.setCreatedBy(userId);
            return categoryService.deleteCategory(category);
        } catch (Exception e) {
            logger.error("菜单删除错误", e);
            return AppResponse.notFound("删除失败");
        }
    }

    /**
     * @Dept：Neusoft
     * @Description：新增分类
     * @Author：shm12q
     * @Date: 2019/4/10
     * @Param：分类信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @PostMapping("addCategory")
    @ApiOperation("添加分类")
    public AppResponse addCategory( Category category) {
        try {
            //获取用户id
            SysUser sysUser = (SysUser)redisTemplate.opsForValue().get(category.getTokenBackend());
            String userId = sysUser.getUserUuid();
            category.setLastModifiedBy(userId);
            category.setCreatedBy(userId);
            return categoryService.addCategory(category);
        } catch (Exception e) {
            logger.error("分类新增异常", e);
            return AppResponse.notFound("新增失败");
        }
    }

    /**
     * @Dept：Neusoft
     * @Description：修改分类
     * @Author：shm12q
     * @Date: 2019/4/10
     * @Param：null
     * @Return：com.neusoft.common.response.AppResponse
     */
    @PutMapping("updateCategory")
    @ApiOperation("修改分类")
    public AppResponse updateCategory(  Category category) {
        try {
            //获取用户id
            SysUser sysUser = (SysUser)redisTemplate.opsForValue().get(category.getTokenBackend());
            String userId = sysUser.getUserUuid();
            category.setLastModifiedBy(userId);
            category.setCreatedBy(userId);
            return categoryService.updateCategory(category);
        } catch (Exception e) {
            logger.error("分类修改异常", e);
            return AppResponse.notFound("修改失败");
        }
    }
}
