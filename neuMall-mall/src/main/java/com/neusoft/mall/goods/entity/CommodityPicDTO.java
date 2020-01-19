package com.neusoft.mall.goods.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: CommodityPic
 * @Description:
 * @Author: shm12q
 * @Date: 2019/4/9
 */
@Data
public class CommodityPicDTO {
    private String pictureAddress;//轮播图地址
    private int pictureIsFistPicture;//是否首图1是0否
}
