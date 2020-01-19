package com.neusoft.mall.commoditycenter.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: CommodityDetail
 * @Description:
 * @Author: Steven
 * @Date: 2019/4/17
 */
@Data
public class CommodityDetail extends BaseEntity {
    String commodityCode;
    String pictureAddress;
    String commodityName;
    String commodityDesc;
    String commodityRetailPrice;
    String commodityOriginalPrice;
    String commodityColor;
    String commodityIsLack;
    String commodityUnitId;
    String commityUnitName;
    String commodityTotalCount;
    List<CommodityInfo> pictureList;
    String isCollect;
    private String tokenFront;
}