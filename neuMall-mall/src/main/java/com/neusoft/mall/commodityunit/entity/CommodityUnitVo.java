package com.neusoft.mall.commodityunit.entity;

import com.neusoft.common.entity.BasePageVo;
import lombok.Data;

/**
 * @ClassName: CommodityUnitVo
 * @Description:
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@Data
public class CommodityUnitVo extends BasePageVo {
    private String unitName;
    private String tokenBackend;
}