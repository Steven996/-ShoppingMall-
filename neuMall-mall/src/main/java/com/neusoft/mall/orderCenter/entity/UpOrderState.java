package com.neusoft.mall.orderCenter.entity;
import lombok.Data;

import java.util.List;
/**
* @Description:    更新订单状态所需参数的实体
* @Author:         李瑞鑫
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Data
public class UpOrderState{
    List<String> orderNumberList;
    String orderState;
    String tokenFront;
}
