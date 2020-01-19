package com.neusoft.mall.orderCenter.entity;
import lombok.Data;
/**
 * @Description:    获取所有订单所需参数实体
 * @Author:         李瑞鑫
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
public class GetOrderList {
    String orderNumber;
    String orderDateStart;
    String orderDateEnd;
    String orderState;
    String tokenFront;
}
