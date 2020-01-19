package com.neusoft.mall.homePage.controller;

import com.neusoft.common.entity.PageVo;
import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.category.entity.Category;
import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.goods.entity.Commodity;
import com.neusoft.mall.homePage.service.HomePageService;
import com.neusoft.mall.homePage.vo.RecommondCommodityVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;
/**
 * @Description:    首页展示接口 包含：推荐商品展示、买过的商品展示、分类信息
 * @Author:         李瑞鑫
 * @Version:        1.0
 */
@Api("HomePageController相关的api")
@EnableSwagger2
@Slf4j
@RestController
@RequestMapping(value = "/front/commodity")
public class HomePageController {
    @Autowired
    HomePageService homePageService;
    @Autowired
    RedisTemplate redisTemplate ;
    /**
     * @Description:    获取被推荐的商品列表
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    @GetMapping(value = "getRecommondCommodityList")
    public AppResponse getRecommondCommodityList(String commodityIsRecommend,
                                                 RecommondCommodityVo recommondCommodityVo)
    {
        PageVo<Commodity> pageVo;
        pageVo = homePageService.getRecommondCommodityList(commodityIsRecommend,recommondCommodityVo);
        return AppResponse.success("查询推荐商品成功",pageVo);
    }
    /**
     * @Description:    获取买过的商品列表
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    @GetMapping(value = "getBuyCommodityList")
    public AppResponse getBuyCommodityList(String tokenFront){
        Customer customer = (Customer)redisTemplate.opsForValue().get(tokenFront);
        if(customer==null){
            return AppResponse.notFound("未登录");
        }
        List<Commodity> commodityList = homePageService.getBuyCommodityList(customer.getCustomerId());
        return AppResponse.success("已购买的商品",commodityList);
    }
    /**
     * @Description:    分类信息
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    @GetMapping(value = "getClassifyList")
    public AppResponse getClassifyList(String categoryParentId){
        List<Category> categoryList = homePageService.getClassifyList(categoryParentId);
        return AppResponse.success("分类信息",categoryList);
    }

}
