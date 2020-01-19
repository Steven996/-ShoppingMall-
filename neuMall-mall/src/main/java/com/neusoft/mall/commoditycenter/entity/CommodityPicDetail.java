package com.neusoft.mall.commoditycenter.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: CommodityPicDetail
 * @Description:
 * @Author: Steven
 * @Date: 2019/4/17
 */
@Data
public class CommodityPicDetail extends BaseEntity {
    String pictureAddress;
    String pictureIsFistPicture;
    private String tokenFront;
}