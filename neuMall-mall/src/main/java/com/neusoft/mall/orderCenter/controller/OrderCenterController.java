package com.neusoft.mall.orderCenter.controller;

import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.orderCenter.entity.GetOrderList;
import com.neusoft.mall.orderCenter.entity.Order;
import com.neusoft.mall.orderCenter.entity.UpOrderState;
import com.neusoft.mall.orderCenter.service.OrderCenterService;
import com.neusoft.mall.orderCenter.vo.OrderVo;
import com.neusoft.mall.orderCenter.vo.PageVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;

@Api("TestController相关的api")
@EnableSwagger2
@Slf4j
@RestController
@RequestMapping(value = "/front/orderCenter")
public class OrderCenterController {
    @Autowired
    OrderCenterService orderCenterService;
    @Autowired
    RedisTemplate redisTemplate;
    /**
     * @Description:    按条件获取订单列表  条件：订单编号、订单日期、订单状态
     * @Author:         李瑞鑫
     * @UpdateRemark:   暂无
     * @Version:        1.0
     */
    @GetMapping(value = "getOrderList")
    public AppResponse getOrderList(GetOrderList getOrderList,OrderVo orderVo)
    {
        Customer customer = (Customer)redisTemplate.opsForValue().get(getOrderList.getTokenFront());
        String customerId =customer.getCustomerId();
        PageVo<Order> orderlist = new PageVo<>();
        orderlist = orderCenterService.getOrderList(getOrderList,customerId,orderVo);
        return AppResponse.success("查询成功",orderlist);
    }
    /**
     * @Description:    查看订单详情
     * @Author:         李瑞鑫
     * @Version:        1.0
     */
    @GetMapping(value = "getOrderDetail")
    public AppResponse getOrderDetail(String orderId,String tokenFront){
        Customer customer = (Customer)redisTemplate.opsForValue().get(tokenFront);
        if(customer!=null){
            Order order = orderCenterService.getOrderByOrderId(orderId);
            return AppResponse.success("商品详情",order);
        }   return AppResponse.success("未登录");
    }
    /**
     * @Description:    确认收货 以及 撤销订单
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    @PutMapping(value = "updateOrderStatus")
    public AppResponse updateOrderStatus(@RequestBody UpOrderState upOrderState){
        Customer customer = (Customer)redisTemplate.opsForValue().get(upOrderState.getTokenFront());
        if(customer==null){return AppResponse.notFound("用户未登录");}
        orderCenterService.updateOrderStatus(upOrderState.getOrderNumberList(),
                                             upOrderState.getOrderState(),
                                             customer.getCustomerId());
        return  AppResponse.success("订单列表状态已修改");
    }
}
