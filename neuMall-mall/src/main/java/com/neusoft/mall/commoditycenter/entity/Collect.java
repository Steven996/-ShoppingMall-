package com.neusoft.mall.commoditycenter.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: Collect实体
 * @Description:
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@Data
public class Collect extends BaseEntity {
    private String collectId;
    private String customerId;
    private String commodityId;
    private String tokenFront;
}