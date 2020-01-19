package com.neusoft.mall.goods.controller;



import com.neusoft.admin.accountAdmin.entity.SysUser;
import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.goods.entity.Commodity;
import com.neusoft.mall.goods.entity.CommodityVo;


import com.neusoft.mall.goods.entity.UpdateGoods;
import com.neusoft.mall.goods.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserController
 * @Description:
 * @Author: shm12q
 * @Date: 2019/4/1
 */
@RestController
@RequestMapping("backend/goods")
@Slf4j
@Api("商品管理")
public class GoodsController {

    @Resource
    GoodsService goodsService;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * @Dept：Neusoft
     * @Description：获取商品列表
     * @Author：shm12q
     * @Date: 2019/4/15
     * @Param：CommodityVo
     */
    @GetMapping(value = "getGoodsList")
    @ApiOperation("获取商品列表")
    public AppResponse getGoodsList(CommodityVo commodityVo) throws Exception {
        try {
            return goodsService.getGoodsList(commodityVo);
        } catch (Exception e) {
            log.error("用户获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }

    /**
     * @Dept：Neusoft
     * @Description：修改商品
     * @Author：shm12q
     * @Date: 2019/4/23
     * @Param：null
     * @Return：
     */
    @PutMapping("updateGoods")
    @ApiOperation("更新商品")
    public AppResponse updateGoods(@RequestBody UpdateGoods updateGoods) throws Exception {
        try {
            //获取用户id
            SysUser sysUser = (SysUser)redisTemplate.opsForValue().get(updateGoods.getTokenBackend());
            String userId = sysUser.getUserUuid();
            updateGoods.setLastModifiedBy(userId);
            return goodsService.updateGoods(updateGoods);
        } catch (Exception e) {
            log.error("修改商品信息错误", e);
            throw new Exception("系统错误，请重试");
        }
    }

    /**
     * @Dept：Neusoft
     * @Description：上架/下架商品
     * @Author：shm12q
     * @Date: 2019/4/23
     * @Param：null
     * @Return：
     */
    @PutMapping("updateGoodsIsSell")
    @ApiOperation("修改商品上架信息")
    public AppResponse updateGoodsIsSell( Commodity commodity) throws Exception {
        try {
            //获取用户id
            SysUser sysUser = (SysUser)redisTemplate.opsForValue().get(commodity.getTokenBackend());
            String userId = sysUser.getUserUuid();
            commodity.setLastModifiedBy(userId);
            return goodsService.updateGoodsIsSell(commodity);
        } catch (Exception e) {
            log.error("修改上架信息错误", e);
            throw new Exception("系统错误，请重试");
        }
    }

    /**
     * @Dept：Neusoft
     * @Description：获取商品详情
     * @Author：shm12q
     * @Date: 2019/4/23
     * @Param：null
     * @Return：
     */
    @GetMapping(value = "getGoodsDetail")
    @ApiOperation("商品详情")
    public AppResponse getGoodsDetail(Commodity commodity) throws Exception {
        try {
            return goodsService.getGoodsDetail(commodity);
        } catch (Exception e) {
            log.error("商品详情获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }

    /**
     * @Dept：Neusoft
     * @Description：添加商品
     * @Author：shm12q
     * @Date: 2019/4/23
     * @Param：null
     * @Return：
     */
    @PostMapping("addGoods")
    @ApiOperation("添加商品")
    public AppResponse addGoods(@RequestBody UpdateGoods goods) throws Exception {
        try {
            //获取用户id
            SysUser sysUser = (SysUser)redisTemplate.opsForValue().get(goods.getTokenBackend());
            String userId = sysUser.getUserUuid();
            goods.setCreatedBy(userId);
            goods.setLastModifiedBy(userId);
            AppResponse appResponse = goodsService.addGoods(goods);
            return appResponse;
        } catch (Exception e) {
            log.error("商品新增失败", e);
            throw new Exception("商品新增失败，请重试");
        }
    }

    /**
     * @Dept：Neusoft
     * @Description：删除商品
     * @Author：shm12q
     * @Date: 2019/4/23
     * @Param：null
     * @Return：
     */
    @PutMapping("deleteGoods")
    @ApiOperation("删除商品")
    public AppResponse deleteGoods(Commodity commodity) throws Exception {
        try {
            //获取用户id
            SysUser sysUser = (SysUser)redisTemplate.opsForValue().get(commodity.getTokenBackend());
            String userId = sysUser.getUserUuid();
            commodity.setLastModifiedBy(userId);
            return goodsService.deleteGoods(commodity);
        } catch (Exception e) {
            log.error("商品删除错误", e);
            throw new Exception("商品删除错误");
        }
    }

    /**
     * @Dept：Neusoft
     * @Description：上传图片
     * @Author：shm12q
     * @Date: 2019/4/23
     * @Param：null
     * @Return：
     */
    @PostMapping("uploadFile")
    @ApiOperation("上传图片")
    public AppResponse uploadFile(@RequestParam("tokenBackend")String tokenBackend, @RequestParam("file")MultipartFile multipartFile[]) throws Exception {
        try {
            Map<String, Object> param = new HashMap<>();
            //获取用户id
            SysUser sysUser = (SysUser)redisTemplate.opsForValue().get(tokenBackend);
            String userId = sysUser.getUserUuid();
            param.put("userId", userId);
            param.put("files", multipartFile);
            return goodsService.uploadFile(param);
        } catch (Exception e) {
            log.error("上传图片失败", e);
            throw new Exception("上传图片出错");
        }
    }
}

