package com.neusoft.mall.goods.service;

import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.goods.entity.Commodity;
import com.neusoft.mall.goods.entity.CommodityVo;
import com.neusoft.mall.goods.entity.UpdateGoods;

import java.util.Map;

public interface GoodsService {
    AppResponse getGoodsList(CommodityVo commodityVo);

    AppResponse updateGoodsIsSell(Commodity commodity);

    AppResponse getGoodsDetail(Commodity commodity);

    AppResponse updateGoods(UpdateGoods updateGoods);

    AppResponse addGoods(UpdateGoods goods);

    AppResponse deleteGoods(Commodity commodity);

    AppResponse uploadFile(Map<String, Object> param);

    int deletePics(String commodityId);
}
