package com.neusoft.mall.goods.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: UpdateGoods
 * @Description:商品前端传输数据
 * @Author: shm12q
 * @Date: 2019/4/15
 */
@Data
public class UpdateGoods extends BaseEntity {
    private String commodityCode;//商品编号
    private String commodityId;//商品ID
    private String commodityName;//商品名称
    private String commodityFirstPicture;//商品首图
    private String categoryFirst;//一级分类id
    private String categorySecond;//二级分类id
    private String commodityDesc;//商品详情
    private BigDecimal commodityOriginalPrice;//原价
    private BigDecimal commodityRetailPrice;//零售价
    private int commodityIsSold;//是否上架1是0否
    private int commodityInventory;//库存
    private int commodityIsLack;//是否缺货1是0否
    private int commodityTotalCount;//销量
    private String commodityColor;//颜色
    private String commodityUnitId;//商品单位ID
    private int commodityIsRecommend;//是否推荐
    private List<CommodityPicDTO> pictureList;//轮播图信息
    private String tokenBackend;
}
