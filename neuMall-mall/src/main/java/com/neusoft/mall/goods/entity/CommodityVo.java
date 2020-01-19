package com.neusoft.mall.goods.entity;

import com.neusoft.common.entity.BaseEntity;
import com.neusoft.common.entity.BasePageVo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName: CommodityVo
 * @Description:
 * @Author: shm12q
 * @Date: 2019/4/15
 */
@Data
public class CommodityVo extends BasePageVo {
    private String commodityCode;//商品编号
    private String commodityId;//商品id
    private String commodityName;//商品名称
    private String commodityFirstPicture;//商品首图
    private String commodityColor;//颜色
    private BigDecimal commodityOriginalPrice;//原价
    private BigDecimal commodityRetailPrice;//零售价
    private String commodityUnitId;//购买单位
    private Integer commodityIsSold;//是否上架1是0否
    private int commodityIsRecommend;//是否推荐1是0否
    private int commodityInventory;//库存
    private int commodityIsLack;//是否缺货1是0否
    private int commodityTotalCount;//销量
    private String categoryFirst;//一级分类id
    private String categorySecond;//二级分类id
    private String tokenBackend;
}
