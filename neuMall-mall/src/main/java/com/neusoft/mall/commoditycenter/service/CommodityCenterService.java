package com.neusoft.mall.commoditycenter.service;

import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.commoditycenter.entity.*;
import com.neusoft.mall.orderCenter.entity.OrderDetail;

public interface CommodityCenterService {
    AppResponse addShoppingCart(ShopDto shopDto);
    AppResponse addOrder(OrderDto orderDto);
    AppResponse getCommodityBuyNow(OrderDetail orderDetail);
    AppResponse getCommodityCenterTrading(String commodityId);
    AppResponse getCommodityCenterSimilar(String categoryFirst);
    AppResponse getCommodityList(CommodityVO commodityVO);
    AppResponse getCommodityCenterDeatil(CommodityDetailIn commodityDetailIn);
    AppResponse commodityCollection(CommodityCollectPro commodityCollectPro);
    AppResponse commodityCollectionList(CommodityInfo commodityInfo);
}
