package com.neusoft.mall.goods.dao;

import com.neusoft.mall.goods.entity.*;

import java.util.List;

public interface GoodsDao {

    List<CommodityDTO> getGoodsList(CommodityVo commodityVo);

    String getCategoryName(String categoryId);

    int updateGoodsIsSell(Commodity commodity);

    GoodsDetail getGoodsDetail(Commodity commodity);

    List<CommodityPicDTO> getGoodsPics(String commodityId);

    int deleteGoods(Commodity commodity);

    int addGoods(UpdateGoods goods);

    int addPic(CommodityPic pic);

    int deletePics(String commodityId);

    int updateGoods(UpdateGoods goods);
}
