package com.neusoft.mall.commodityunit.dao;

import com.neusoft.mall.commodityunit.entity.CommodityUnit;
import com.neusoft.mall.commodityunit.entity.CommodityUnitVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public interface CommodityUnitDao {
    List<CommodityUnit> getCommodityUnitListPage(CommodityUnitVo commodityUnitVo);
    List<CommodityUnit> getExistCommodityUnit(String unitName);
    CommodityUnit getIfDeleted(String unitName);
    List<CommodityUnit> getCommodityUnitList();
    int addCommodityUnit(CommodityUnit commodityUnit);
    int updateCommodityUnit(CommodityUnit commodityUnit);
    List<CommodityUnit> getCommodityUnitDetail(CommodityUnit commodityUnit);
    int  deleteCommodityUnit(CommodityUnit commodityUnit);
    int addVersion(CommodityUnit commodityUnit);
}
