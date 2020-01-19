package com.neusoft.mall.addressManagement.service.impl;

import com.neusoft.mall.addressManagement.dao.AddressManagementDao;
import com.neusoft.mall.addressManagement.entity.Receive;
import com.neusoft.mall.addressManagement.entity.ReceiveVo;
import com.neusoft.mall.addressManagement.entity.RegionVo;
import com.neusoft.mall.addressManagement.service.AddressManagementService;
import com.neusoft.mall.commoditycenter.entity.Region;
import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.util.StringLengthEmptyUndefind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: AddressManagementServiceImpl
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/20
 */
@Service
public class AddressManagementServiceImpl implements AddressManagementService {
    @Resource
    AddressManagementDao addressDao;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<ReceiveVo> getReceiveListService(Customer customer){

        Customer customer1=(Customer) redisTemplate.opsForValue().get(customer.getTokenFront());

        customer1.setCustomerId(customer1.getCustomerId().trim());
        List<ReceiveVo> list=addressDao.getReceiveListDao(customer1.getCustomerId());
        for (int i=0;i<list.size();i++){
            list.get(i).setRegionCityName(addressDao.findAddrename(list.get(i).getRegionCity()));
            list.get(i).setRegionCountyName(addressDao.findAddrename(list.get(i).getRegionCounty()));
            list.get(i).setRegionProvinceName(addressDao.findAddrename(list.get(i).getRegionProvince() ) );
            list.get(i).setReceiveAllAddress(list.get(i).getRegionProvinceName()+list.get(i).getRegionCityName()+list.get(i).getRegionCountyName()+list.get(i).getReceiveDetailedAddress());
        }

        return list;
    }

    //0:失败 大于0，影响的条数
    @Override
    public int addReceive(String receiveContact, String receiveDetailedAddress, String regionProvince,
                          String regionCity, String regionCounty, String receiveTel, String customerId) {
        Receive receive=new Receive();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        receive.setReceiveId(uuid);
        receive.setRegionProvince(regionProvince);
        receive.setRegionCity(regionCity);
        receive.setRegionCounty(regionCounty);
        receive.setReceiveDetailedAddress(receiveDetailedAddress);
        receive.setReceiveContact(receiveContact);
        receive.setReceiveTel(receiveTel);
        receive.setReceiveIsDefault(0);
        receive.setCreatedBy(customerId);
        receive.setLastModifiedBy(customerId);
        return addressDao.addReceive(receive);
    }

    @Override
    public int updateReceive(String receiveId, String receiveContact, String receiveDetailedAddress,
                             String regionProvince, String regionCity, String regionCounty,
                             String receiveTel, int version) {
        Receive receive=new Receive();
        receive.setReceiveId(receiveId);
        receive.setRegionProvince(regionProvince);
        receive.setRegionCity(regionCity);
        receive.setRegionCounty(regionCounty);
        receive.setReceiveDetailedAddress(receiveDetailedAddress);
        receive.setReceiveContact(receiveContact);
        receive.setReceiveTel(receiveTel);
        receive.setVersion(version);
        return addressDao.updateReceive(receive);
    }

    @Override
    public int deleteReceive(String receiveId) {
        return addressDao.deleteReceive(receiveId);
    }

    @Override
    public int updateReceiveByDefault(String receiveId, int version,String customerId) {

        System.out.println("receiveId:"+receiveId+" version:"+version);
        addressDao.updateReceiveByDefaultVo(customerId);
        return addressDao.updateReceiveByDefault(receiveId,version);
    }

    @Override
    public List<RegionVo> getRegionService(String parentRegionID) {
        if(parentRegionID.equals("0")){
            return addressDao.getRegionDao1();
        }
        return addressDao.getRegionDao(parentRegionID);
    }

    @Override
    public ReceiveVo getReceiveDetailService(String receiveId) {
        ReceiveVo receiveVo=addressDao.getReceiveDetailDao(receiveId);
        receiveVo.setRegionCityName(addressDao.findAddrename(receiveVo.getRegionCity()));
        receiveVo.setRegionCountyName(addressDao.findAddrename(receiveVo.getRegionCounty()));
        receiveVo.setRegionProvinceName(addressDao.findAddrename(receiveVo.getRegionProvince()));
        return receiveVo;
    }


}
