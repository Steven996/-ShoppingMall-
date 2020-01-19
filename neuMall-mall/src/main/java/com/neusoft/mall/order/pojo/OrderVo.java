package com.neusoft.mall.order.pojo;

import com.neusoft.common.entity.BasePageVo;
import com.neusoft.common.entity.PageVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
/**
 * @ClassName: OrderChangeStatusVo
 * @Description:pojo
 * @Author: Steven
 * @Date: 2019/4/16
 */
@Data
public class OrderVo extends BasePageVo {
    private String orderNumber;
    private String orderState;
    private String orderDateStart;
    private String orderDateEnd;
    private String tokenBackend;
}