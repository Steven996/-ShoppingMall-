package com.neusoft.mall.commoditycenter.entity;

import lombok.Data;

/**
 * @ClassName: TradeVo
 * @Description: 用于输出交易记录实体
 * @Author: hanjz
 * @Date: 2019/4/13
 */
@Data
public class TradeVo {
    private String createdBy;
    private String customerName;
    private String gmtCreate;
    private String commodityPrice;
    private String commodityNum;
    private String version;
    private String tokenFront;
}