package com.neusoft.mall.order.controller;

import com.neusoft.common.response.AppResponse;
import com.neusoft.common.response.AppResponseStatus;
import com.neusoft.mall.order.pojo.OrderChangeStatusVo;
import com.neusoft.mall.order.service.OrderService;
import com.neusoft.mall.order.pojo.OrderVo;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: OrderController
 * @Description:OrderController
 * @Author: Steven
 * @Date: 2019/4/9
 */
    @Slf4j
    @RestController
    @RequestMapping("/backend/order")
    @Api("订单模块")
    public class OrderController {
    @Autowired
    OrderService orderService;
    /**
     * @ClassName: OrderController
     * @Description:订单查询
     * @Author: Steven
     * @Date: 2019/4/9
     */
    @GetMapping("getOrders")  /*订单查询*/
    @ApiOperation("订单查询")
    public AppResponse getOrders(OrderVo orderVo){
            return orderService.getOrders(orderVo);
        }
    /**
     * @ClassName: OrderController
     * @Description:订单详情
     * @Author: Steven
     * @Date: 2019/4/9
     */
    @ApiOperation("订单详情")
    @GetMapping("getOrderDetail")  /*订单详情*/
    public AppResponse getOrderDetail(String orderId){
            return orderService.getOrderDetail(orderId);
        }
    /**
     * @ClassName: OrderController
     * @Description:订单发货
     * @Author: Steven
     * @Date: 2019/4/9
     */
    @ApiOperation("订单发货")
    @PutMapping("updateOrderStatus")  /*订单发货*/
    public AppResponse updateOrderStatus(@RequestBody OrderChangeStatusVo vo){
             return orderService.updateOrderStatus(vo);
        }
}