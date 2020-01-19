package com.neusoft.mall.shoppingCart.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: Shop
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/9
 */
@Data
public class Shop extends BaseEntity { //数据库对应表：t_shop
    private  String  shopId;
    private  String commodityId;
    private  String shopNumber;
    private  String customerId;
    private  String token;
    private  String tokenFront;
    private  String tokenBackend;


}
