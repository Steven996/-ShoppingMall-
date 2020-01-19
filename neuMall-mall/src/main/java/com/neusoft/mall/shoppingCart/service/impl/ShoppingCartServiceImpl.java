package com.neusoft.mall.shoppingCart.service.impl;

import com.neusoft.mall.shoppingCart.entity.Shop;
import com.neusoft.mall.shoppingCart.dao.ShoppingCartDao;
import com.neusoft.mall.shoppingCart.entity.ShopVo;
import com.neusoft.mall.shoppingCart.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: ShoppingCartServiceImpl
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/16
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    ShoppingCartDao shoppingCartDao;

    @Override
    public List<ShopVo> findShopAllService(String customerId) {
        customerId.trim();
        return shoppingCartDao.findShopAll(customerId);
    }

    @Override
    public int deleteShoppingCartService(String shopId) {
        shopId.trim();
        return shoppingCartDao.deleteShoppingCart(shopId);
    }
}
