package com.neusoft.mall.commoditycenter.dao;


import com.neusoft.mall.commoditycenter.entity.*;
import com.neusoft.mall.goods.entity.Commodity;
import com.neusoft.mall.goods.entity.CommodityPic;
import com.neusoft.mall.shoppingCart.entity.Shop;
import com.neusoft.mall.orderCenter.entity.Order;
import com.neusoft.mall.orderCenter.entity.OrderDetail;

import java.util.List;

public interface CommodityCenterDao {
    int addShoppingCart(Shop shop);
    int addOrder(Order order);
    List<Order> addOrderSuccess(Order order);
    int addOrderDetail(OrderDetail orderDetail);
    Commodity getPrice(String commodityId);
    CommodityPic getPictureAddress(String commodityId);
    List<TradeVo> getCommodityCenterTrading(String commodityId);
    List<Commodity> getCommodityCenterSimilar(String categoryFirst);
    List<CommodityInfo> getCommodityList(CommodityVO commodityVO);
    CommodityDetail getCommodityCenterDeatil(CommodityDetailIn commodityDetailIn);
    List<CommodityInfo> getCommodityCentPic(CommodityDetailIn commodityDetailIn);
    int commodityCollection(CommodityCollect cct);
    int commodityUnCollection(CommodityCollect cct);
    List<CommodityInfo> commodityCollectionList(CommodityInfo commodityInfo);
}
