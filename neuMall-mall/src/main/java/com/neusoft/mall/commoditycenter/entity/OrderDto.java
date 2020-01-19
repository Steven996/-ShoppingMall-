package com.neusoft.mall.commoditycenter.entity;


import com.neusoft.mall.orderCenter.entity.OrderDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: OrderDto
 * @Description: 提交订单接收前端参数实体
 * @Author: hanjz
 * @Date: 2019/4/11
 */
@Data
public class OrderDto {
    private String orderAddress;
    private String receiveTel;
    private String receiveContact;
    private String orderRemark;
    private List<OrderDto2> commodityList;
    private String tokenFront;

}