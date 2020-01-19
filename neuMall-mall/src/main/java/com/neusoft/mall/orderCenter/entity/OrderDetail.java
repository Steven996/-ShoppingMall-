package com.neusoft.mall.orderCenter.entity;
import com.neusoft.common.entity.BaseEntity;
import com.neusoft.mall.commoditycenter.entity.OrderDto2;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description:    订单详情实体
 * @Author:         李瑞鑫
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
public class OrderDetail extends BaseEntity {
    private String orderDetailId;
    private String orderId;
    private String commodityId;
    private BigDecimal commodityPrice;
    private Integer commodityNum;
    private BigDecimal commodityTotalPrice;
    private String commodityFirstPicture;
    private String commodityName;
    private String commodityCode;
    private String tokenFront;
    private List<OrderDto2> commodityList=new ArrayList();
}
