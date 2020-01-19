package com.neusoft.mall.order.pojo;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: OrderChangeStatusVo
 * @Description:pojo
 * @Author: Steven
 * @Date: 2019/4/16
 */
@Data
public class OrderChangeStatusVo extends BaseEntity {
    private List<String> orderNumberList;
    private String orderState;
}