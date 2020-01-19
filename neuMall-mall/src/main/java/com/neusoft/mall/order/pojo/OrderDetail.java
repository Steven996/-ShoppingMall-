package com.neusoft.mall.order.pojo;

import lombok.Data;

/**
 * @ClassName: OrderDetail
 * @Description:
 * @Author: Steven
 * @Date: 2019/5/4
 */
@Data
public class OrderDetail {
    private String commodityFirstPicture;
    private String commodityName;
    private String commodityCode;
    private String commodityPrice;
    private String commodityNum;
    private String commodityTotalPrice;
}