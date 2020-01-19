package com.neusoft.mall.commodityunit.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: CommodityUnit
 * @Description:
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@Data
public class CommodityUnit extends BaseEntity {
    private String unitId;
    private String unitName;
    private String tokenBackend;
}