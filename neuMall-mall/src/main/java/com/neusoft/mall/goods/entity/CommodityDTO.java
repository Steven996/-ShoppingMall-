package com.neusoft.mall.goods.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName: CommodityDTO
 * @Description:返回商品数据
 * @Author: shm12q
 * @Date: 2019/4/15
 */
@Data
public class CommodityDTO {
    private String commodityCode;//商品编号
    private String pictureAddress;//商品首图
    private String commodityId;//商品id
    private String commodityName;//商品名称
    private BigDecimal commodityOriginalPrice;//原价
    private BigDecimal commodityRetailPrice;//零售价
    private int commodityIsSold;//是否上架1是0否
    private int commodityTotalCount;//销量
    private String categoryFirst;//一级分类id
    private String categoryFirstName;//一级分类名称
    private String categorySecond;//二级分类id
    private String categorySecondName;//二级分类名称
    private int version;//版本号
    private String commodityColor;//颜色
    private String commodityUnitId;//商品单位ID
    private int commodityIsRecommend;//是否推荐
    private String commodityDesc;//商品详情
}
