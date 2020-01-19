package com.neusoft.mall.shoppingCart.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: ShopVo
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/16
 */
@Data
public class ShopVo{
    private  String  shopId;
    private  String commodityId;
    private  String commodityCode;
    private  String pictureAddress;
    private  String commodityName;
    private  String commodityRetailPrice;
    private  String commodityNum;

}
