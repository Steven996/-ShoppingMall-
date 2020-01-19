package com.neusoft.mall.commoditycenter.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: CommodityCollectPro
 * @Description:
 * @Author: Steven
 * @Date: 2019/4/18
 */
@Data
public class CommodityCollectPro extends BaseEntity{
//    private String tokenFront;//前端token
    private List<CommodityCollect> collectionList;
}