package com.neusoft.mall.commoditycenter.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: Receive实体
 * @Description:
 * @Author: hanjz
 * @Date: 2019/4/11
 */
@Data
public class Receive extends BaseEntity {
    private String receiveId;
    private String regionProvince;
    private String regionCity;
    private String regionCounty;
    private String receiveDetailedAddress;
    private String receiveContact;
    private String receiveTel;
    private int receiveIsDefault;
    private String tokenFront;
}