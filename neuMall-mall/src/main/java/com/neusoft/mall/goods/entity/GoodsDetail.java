package com.neusoft.mall.goods.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: GoodsDetail
 * @Description:商品详情+轮播图信息
 * @Author: shm12q
 * @Date: 2019/4/15
 */
@Data
public class GoodsDetail {
    private String commodityName;//商品名称
    private String categoryFirst;//一级分类id
    private String categoryFirstName;//一级分类名称
    private String categorySecond;//二级分类id
    private String categorySecondName;//二级分类名称
    private BigDecimal commodityOriginalPrice;//原价
    private BigDecimal commodityRetailPrice;//零售价
    private int commodityIsSold;//是否上架1是0否
    private int commodityInventory;//库存
    private String commodityColor;//颜色
    private String commodityUnitId;//商品单位ID
    private int commodityIsRecommend;//是否推荐
    private List<CommodityPicDTO> pictureList;//轮播图信息
    private int version;
}
