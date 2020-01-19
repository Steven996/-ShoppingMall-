package com.neusoft.mall.shoppingCart.controller;

import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.shoppingCart.entity.Shop;
import com.neusoft.mall.shoppingCart.entity.ShopVo;
import com.neusoft.mall.shoppingCart.service.ShoppingCartService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: ShoppingCartController
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/16
 */
@RestController
@Slf4j
@RequestMapping("front/shoppingCart")
@Api
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "getShoppingCartList",method = RequestMethod.GET )
    public AppResponse getShoppingCartList(Shop shop1){
        //查tokenBackend
        Customer customer=(Customer)redisTemplate.opsForValue().get(shop1.getTokenFront());
        List<ShopVo> shop = shoppingCartService.findShopAllService(customer.getCustomerId());

        System.out.println("front/shoppingCart/getShoppingCartList "+" customerId:"+customer.getCustomerId());

        if(shop.size()>0){
                return AppResponse.success("请求成功",shop);
        }else if(shop.size()==0){
            return AppResponse.notFound("notfind"+shop);
        }
        return AppResponse.bizError("未知错误"+shop);
    }

    @DeleteMapping("deleteShoppingCart")
    public AppResponse deleteShoppingCart(Shop shop1){
        //查tokenBackend
        System.out.println("front/shoppingCart/deleteShoppingCart "+" shopId:"+shop1.getShopId());
        int a = shoppingCartService.deleteShoppingCartService(shop1.getShopId());
        System.out.println("a"+a);
        if(a==0){
            return AppResponse.bizError("未找到该购物车");
        }else if(a==1){
            return AppResponse.success("删除成功");
        }
        return AppResponse.bizError("未知错误");
    }

}
