package com.neusoft.mall.addressManagement.controller;

import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.addressManagement.entity.*;
import com.neusoft.mall.addressManagement.service.AddressManagementService;
import com.neusoft.mall.customer.entity.Customer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: AddressManagementController
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/20
 */
@RestController
@Slf4j
@RequestMapping("front/receive")
@Api
public class AddressManagementController {
    @Resource
    AddressManagementService addressService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("getReceiveList")
    public AppResponse getReceiveList(Customer customer){
        //查tokenBackend
        System.out.println("front/receive/getReceiveList");
        List<ReceiveVo> list=addressService.getReceiveListService(customer);
        //System.out.println("front/receive/getReceiveList"+" customerId:"+customer.getCustomerId()+" len"+list.size());
        return AppResponse.success("获取成功",list);
    }


    @PostMapping("addReceive")
    public AppResponse addReceive(AddReceive addReceive){
        //查tokenBackend
        System.out.println("front/receive/addReceive");
        int resultInt=addressService.addReceive( addReceive.getReceiveContact(), addReceive.getReceiveDetailedAddress(), addReceive.getRegionProvince(),
                addReceive.getRegionCity(), addReceive.getRegionCounty(), addReceive.getReceiveTel(), addReceive.getCustomerId());
        //System.out.println("front/receive/addReceive"+" resultInt:"+resultInt);

        if(resultInt==1){
            return AppResponse.success("添加成功");
        }
        return AppResponse.bizError("添加失败");
    }

    @PutMapping("updateReceive")
    public AppResponse updateReceive(UpdateReceiveBack upDate){
        //查tokenBackend
        System.out.println("front/receive/updateReceive:");

        System.out.println("front/receive/updateReceive:"+upDate.getReceiveId()+" "+upDate.getReceiveContact()+" "+
                upDate.getReceiveDetailedAddress()+" "+upDate.getRegionProvince()+" "+
                upDate.getRegionCity()+" "+upDate.getRegionCounty()+" "+ upDate.getReceiveTel()+upDate.getVersion());
        int resultInt=addressService.updateReceive( upDate.getReceiveId(),upDate.getReceiveContact(),
                upDate.getReceiveDetailedAddress(), upDate.getRegionProvince(),
                upDate.getRegionCity(), upDate.getRegionCounty(), upDate.getReceiveTel(), upDate.getVersion());

        System.out.println("front/receive/updateReceive"+" resultInt:"+resultInt);
        if(resultInt==1){
            return AppResponse.success("修改成功");
        }
        return AppResponse.bizError("修改失败");

    }

    @PutMapping("deleteReceive")
    public AppResponse deleteReceive(String receiveId){
        //查tokenBackend
        int resultInt=addressService.deleteReceive(receiveId);
        System.out.println("front/receive/deleteReceive  receiveId:"+receiveId+" resultInt:"+resultInt);
        if(resultInt==1){
            return AppResponse.success("修改成功");
        }
        return AppResponse.bizError("删除失败");
    }

    @PutMapping("updateReceiveByDefault")
    public AppResponse updateReceiveByDefault(Receive receive){
        //查tokenBackend
        Customer customerOwn=(Customer)redisTemplate.opsForValue().get(receive.getTokenFront());

        System.out.println("front/receive/deleteReceive");
        System.out.println("front/receive/deleteReceive  receiveId:"+receive.getReceiveId()+
                " version:"+receive.getVersion());
        int resultInt=addressService.updateReceiveByDefault(receive.getReceiveId(),receive.getVersion(),customerOwn.getCustomerId());
        System.out.println("front/receive/deleteReceive  receiveId:"+receive.getReceiveId()+
                " version:"+receive.getVersion()+" resultInt:"+resultInt);
        if(resultInt==1){
            return AppResponse.success("设置成功");
        }
        return AppResponse.bizError("设置失败");
    }

    @GetMapping("getRegion")
    public AppResponse getRegion(String parentRegionID){
        List<RegionVo> resultInt=addressService.getRegionService(parentRegionID);
        System.out.println("front/receive/getRegion  parentRegionID:"+parentRegionID+ " resultInt.size:"+resultInt.size());
        System.out.println();
        return AppResponse.success("获取成功",resultInt);
    }

    @GetMapping("getReceiveDetail")
    public AppResponse getReceiveDetail(Receive receive){
        ReceiveVo receive1=addressService.getReceiveDetailService(receive.getReceiveId());

        return AppResponse.success("成功",receive1);
    }
}
