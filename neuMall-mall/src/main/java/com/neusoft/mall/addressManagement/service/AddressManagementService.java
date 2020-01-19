package com.neusoft.mall.addressManagement.service;

import com.neusoft.mall.addressManagement.entity.ReceiveVo;
import com.neusoft.mall.addressManagement.entity.RegionVo;
import com.neusoft.mall.commoditycenter.entity.Region;
import com.neusoft.mall.customer.entity.Customer;

import java.util.List;

/**
 * @ClassName: AddressManagementService
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/20
 */

public interface AddressManagementService {
    List<ReceiveVo> getReceiveListService(Customer customer);
    int addReceive(String receiveContact,String receiveDetailedAddress,String regionProvince,
                   String regionCity,String regionCounty,String receiveTel,String customerId);
    int updateReceive(String receiveId,String receiveContact,String receiveDetailedAddress,String regionProvince,
                   String regionCity,String regionCounty,String receiveTel,int version);
    int deleteReceive(String receiveId);
    int updateReceiveByDefault(String receiveId,int version,String customerId);
    List<RegionVo> getRegionService(String parentRegionID);
    ReceiveVo getReceiveDetailService(String receiveId);
}
