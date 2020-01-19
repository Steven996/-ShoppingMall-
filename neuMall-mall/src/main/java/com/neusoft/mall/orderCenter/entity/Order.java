package com.neusoft.mall.orderCenter.entity;
import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
/**
* @Description:    订单实体
* @Author:         李瑞鑫
* @Version:        1.0
*/
@Data
public class Order extends BaseEntity {
    private String orderId;
    private String orderNumber;
    private BigDecimal orderPrice;
    private String customerId;
    private Integer orderState;
    private String orderDateStart;
    private String orderDateEnd;
    private String orderDate;
    private String customerName;
    private String orderAddress;
    private String receiveTel;
    private String receiveContact;
    private String orderRemark;
    private List<OrderDetail> commodityList;
    private String tokenFront;
}
