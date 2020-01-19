package com.neusoft.mall.commoditycenter.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: CommodityInfo
 * @Description:
 * @Author: Steven
 * @Date: 2019/4/16
 */
@Data
public class CommodityInfo extends BaseEntity {
     String commodityName;
     String sortingTotalCount;
     String sortingRetailPrice;
     String categoryFirst;
     String categorySecond;
     String pictureAddress;
     String pictureIsFistPicture;
     String commodityCode;
     String commodityRetailPrice;
     String commodityOriginalPrice;
     String commodityTotalCount;
     String commodityIsSold;
     String commodityIsLack;
     String commodityInventory;
     String commodityUnitId;
     String commodityId;
     String tokenFront;
     String customerId;

}