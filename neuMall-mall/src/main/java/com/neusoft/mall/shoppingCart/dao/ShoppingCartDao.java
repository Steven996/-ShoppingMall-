package com.neusoft.mall.shoppingCart.dao;

import com.neusoft.mall.shoppingCart.entity.Shop;
import com.neusoft.mall.shoppingCart.entity.ShopVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: ShoppingCartDao
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/16
 */

public interface ShoppingCartDao {
    List<ShopVo> findShopAll(@Param("customerId") String customerId);
    int deleteShoppingCart(@Param("shopId") String shopId);
}
