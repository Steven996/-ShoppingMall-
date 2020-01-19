package com.neusoft.mall.commoditycenter.entity;

import com.neusoft.common.entity.BasePageVo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: CommodityVO
 * @Description:
 * @Author: Steven
 * @Date: 2019/4/16
 */
@Data
public class CommodityVO extends BasePageVo {
    private String sortingTotalCount;
    private String sortingRetailPrice;
    private String categoryFirst;
    private String categorySecond;
    private String tokenFront;
}