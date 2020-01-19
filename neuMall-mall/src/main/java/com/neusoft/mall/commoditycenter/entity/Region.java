package com.neusoft.mall.commoditycenter.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: Region实体
 * @Description:
 * @Author: hanjz
 * @Date: 2019/4/11
 */
@Data
public class Region extends BaseEntity {
    private String regionId;
    private String parentRegionId;
    private String regionType;
    private String regionName;
    private String tokenFront;
}