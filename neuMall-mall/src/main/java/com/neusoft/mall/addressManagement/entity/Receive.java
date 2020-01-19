package com.neusoft.mall.addressManagement.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: Receive
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/20
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
