package com.neusoft.mall.addressManagement.dao;

import com.neusoft.mall.addressManagement.entity.Receive;
import com.neusoft.mall.addressManagement.entity.ReceiveVo;
import com.neusoft.mall.addressManagement.entity.RegionVo;
import com.neusoft.mall.commoditycenter.entity.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: AddressManagementDao
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/20
 */

public interface AddressManagementDao {
    List<ReceiveVo> getReceiveListDao(String customerId);
    int addReceive(Receive receive);
    int updateReceive(Receive receive);
    int deleteReceive(@Param("receiveId") String receiveId);
    int updateReceiveByDefault(@Param("receiveId") String receiveId,@Param("version") int version);
    int updateReceiveByDefaultVo(@Param("customerId") String customerId);
    List<RegionVo> getRegionDao(@Param("parentRegionID") String parentRegionID);
    List<RegionVo> getRegionDao1();
    String findAddrename(@Param("regionId") String regionId);
    ReceiveVo getReceiveDetailDao(@Param("receiveId") String receiveId);
}
