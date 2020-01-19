package com.neusoft.mall.commoditycenter.controller;

import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.commoditycenter.entity.*;
import com.neusoft.mall.commoditycenter.service.impl.CommodityCenterServiceImpl;

import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.orderCenter.entity.OrderDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: CommodityCenterController
 * @Description:
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@RestController
@Slf4j
@RequestMapping("/front/commodityCenter")
@Api(value = "商品中心")
public class CommodityCenterController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CommodityCenterServiceImpl commodityCenterService;
    /**
     * @Dept：南京软件研发中心
     * @Description： 加入购物车
     * @Author：hanjz
     * @Date: 2019/4/11
     * @Param： 自造实体
     * @Return： AppResponse
     */
    @PostMapping(value ="/addShoppingCart")
    @ApiOperation(value = "加入购物车")
    public AppResponse addShoppingCart(ShopDto shopDto){
        return commodityCenterService.addShoppingCart(shopDto);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description：
     * @Author：hanjz
     * @Date: 2019/4/11
     * @Param： 自造实体
     * @Return：
     */
    @PostMapping(value = "/addOrder")
    @ApiOperation(value = "提交订单")
    public AppResponse addOrder(@RequestBody OrderDto orderDto){
        return commodityCenterService.addOrder(orderDto);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调业务层 完成立即购买功能
     * @Author：hanjz
     * @Date: 2019/4/11
     * @Param： commodityId  commodityNum
     * @Return： AppResponse
     */
    @PostMapping(value = "/getCommodityBuyNow")
    @ApiOperation(value = "立即购买")
    public AppResponse getCommodityBuyNow(@RequestBody OrderDetail orderDetail){
        return commodityCenterService.getCommodityBuyNow(orderDetail);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 查看交易记录
     * @Author：hanjz
     * @Date: 2019/4/11
     * @Param： commodityId
     * @Return： AppResponse
     */
    @GetMapping(value = "/getCommodityCenterTrading")
    @ApiOperation(value = "交易记录")
    public AppResponse getCommodityCenterTrading( String commodityId){
        return commodityCenterService.getCommodityCenterTrading(commodityId);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 同类商品推荐
     * @Author：hanjz
     * @Date: 2019/4/11
     * @Param： categoryFirst
     * @Return：  AppResponse
     */
    @GetMapping(value = "/getCommodityCenterSimilar")
    @ApiOperation(value = "同类商品推荐")
    public AppResponse getCommodityCenterSimilar(String categoryFirst){
        return commodityCenterService.getCommodityCenterSimilar(categoryFirst);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 商品中心主界面查询
     * @Author：Steven
     * @Date: 2019/4/16
     * @Param： commodityVO
     * @Return：  AppResponse
     */
    @GetMapping("/getCommodityList")
    @ApiOperation(value = "商品中心主界面查询")
    public AppResponse getCommodityList(CommodityVO commodityVO) {
        AppResponse app = null;
        try{
            app = commodityCenterService.getCommodityList(commodityVO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return app;
    }
    /**
     * @Dept：南京软件研发中心
     * @Description：商品详情查询
     * @Author：Steven
     * @Date: 2019/4/22
     * @Param：commodityDetailIn
     * @Return：com.neusoft.common.response.AppResponse
     */
    @GetMapping("/getCommodityCenterDeatil")
    @ApiOperation(value = "商品详情查询")
    public AppResponse getCommodityCenterDeatil(CommodityDetailIn commodityDetailIn){
        return commodityCenterService.getCommodityCenterDeatil(commodityDetailIn);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description：添加收藏/取消收藏
     * @Author：Steven
     * @Date: 2019/4/22
     * @Param：commodityCollectPro
     * @Return：com.neusoft.common.response.AppResponse
     */
    @PostMapping("commodityCollection")
    @ApiOperation(value = "添加收藏/取消收藏")
    public AppResponse commodityCollection(@RequestBody CommodityCollectPro commodityCollectPro) {
        AppResponse app  =null;
        try{
                    app = commodityCenterService.commodityCollection(commodityCollectPro);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return app;
    }
    /**
     * @Dept：南京软件研发中心
     * @Description：收藏列表
     * @Author：Steven
     * @Date: 2019/4/22
     * @Param：null
     * @Return：
     */
    @PostMapping("commodityCollectionList")
    @ApiOperation(value = "收藏列表")
    public AppResponse commodityCollectionList(CommodityInfo commodityInfo) {
        AppResponse app = null;
        try{
            app = commodityCenterService.commodityCollectionList(commodityInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return app;
    }
}
