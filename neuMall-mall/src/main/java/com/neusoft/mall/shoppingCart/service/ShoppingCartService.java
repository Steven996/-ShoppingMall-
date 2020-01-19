package com.neusoft.mall.shoppingCart.service;

import com.neusoft.mall.shoppingCart.entity.Shop;
import com.neusoft.mall.shoppingCart.entity.ShopVo;

import java.util.List;

/**
 * @ClassName: ShoppingCartService
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/16
 */

public interface ShoppingCartService {
    List<ShopVo> findShopAllService(String customerId);
    int deleteShoppingCartService (String shopId);
}
