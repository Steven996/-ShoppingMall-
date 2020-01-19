package com.neusoft.mall.addressManagement.entity;

import lombok.Data;

/**
 * @ClassName: ReceiveVo
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/20
 */
@Data
public class ReceiveVo {
    private String receiveId;
    private String receiveContact;
    private String receiveDetailedAddress;
    private String regionProvince;
    private String regionProvinceName;
    private String regionCity;
    private String regionCityName;
    private String regionCounty;
    private String regionCountyName;
    private String receiveTel;
    private String receiveAllAddress;
    private int receiveIsDefault;
    private int version;
}
