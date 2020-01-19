package com.neusoft.mall.addressManagement.entity;

import lombok.Data;

/**
 * @ClassName: UpdateReceiveBack
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/23
 */
@Data
public class UpdateReceiveBack {
    private String receiveId;
    private String receiveContact;
    private String receiveDetailedAddress;
    private String regionProvince;
    private String regionCity;
    private String regionCounty;
    private String receiveTel;
    private int version;
}
