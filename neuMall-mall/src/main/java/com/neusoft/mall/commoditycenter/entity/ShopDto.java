package com.neusoft.mall.commoditycenter.entity;

import lombok.Data;

/**
 * @ClassName: ShopDto
 * @Description: 接收前端参数实体
 * @Author: hanjz
 * @Date: 2019/4/11
 */
@Data
public class ShopDto {
    private String commodityId;
    private String shopNumber;
    private String tokenFront;
}