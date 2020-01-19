package com.neusoft.mall.commoditycenter.entity;

import lombok.Data;

/**
 * @ClassName: CommodityList
 * @Description:
 * @Author: hanjz
 * @Date: 2019/4/23
 */
@Data
public class CommodityList {
    private String commodityId;
    private String commodityCode;
    private String pictureAddress;
    private String commodityName;
    private String commodityPrice;
    private String commodityNum;
    private String commodityTotalPrice;
    private String version;
}