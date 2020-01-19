package com.neusoft.mall.commodityunit.service;


import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.commodityunit.entity.CommodityUnit;
import com.neusoft.mall.commodityunit.entity.CommodityUnitVo;

import java.util.Map;

public interface CommodityUnitService {
     AppResponse getCommodityUnitListPage(CommodityUnitVo commodityUnitVo);
     AppResponse getCommodityUnitList();
     AppResponse addCommodityUnit(CommodityUnit commodityUnit);
     AppResponse updateCommodityUnit(CommodityUnit commodityUnit);
     AppResponse getCommodityUnitDetail(CommodityUnit commodityUnit);
     AppResponse deleteCommodityUnit(CommodityUnit commodityUnit);
}
