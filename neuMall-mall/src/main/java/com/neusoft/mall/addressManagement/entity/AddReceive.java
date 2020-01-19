package com.neusoft.mall.addressManagement.entity;

import lombok.Data;

/**
 * @ClassName: AddReceive
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/22
 */
@Data
public class AddReceive {
    private String receiveContact;
    private String receiveDetailedAddress;
    private String regionProvince;
    private String regionCity;
    private String regionCounty;
    private String receiveTel;
    private String customerId;
    private String tokenFront;
}
