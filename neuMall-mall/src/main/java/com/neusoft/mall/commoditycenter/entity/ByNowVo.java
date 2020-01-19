package com.neusoft.mall.commoditycenter.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: ByNowVo
 * @Description: 用于输出立即购买实体
 * @Author: hanjz
 * @Date: 2019/4/13
 */
@Data
public class ByNowVo {
    private String totalPrice;
    private List<CommodityList> commodityList;
}