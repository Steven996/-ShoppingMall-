package com.neusoft.mall.commoditycenter.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: CommodityCollect
 * @Description:
 * @Author: Steven
 * @Date: 2019/4/17
 */
@Data
public class CommodityCollect extends BaseEntity{
    String commodityId;
    String customerId;
    String collectFlag;
    String collectId;
}