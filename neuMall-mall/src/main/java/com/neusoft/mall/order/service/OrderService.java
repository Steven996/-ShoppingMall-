package com.neusoft.mall.order.service;

import com.neusoft.common.response.AppResponse;
import com.neusoft.common.response.AppResponseStatus;
import com.neusoft.mall.order.pojo.OrderChangeStatusVo;
import com.neusoft.mall.order.pojo.OrderVo;

import java.util.List;
/**
 * @ClassName: OrderService
 * @Description:OrderService
 * @Author: Steven
 * @Date: 2019/4/9
 */
public interface OrderService {
    /**
     * @ClassName: OrderService
     * @Description:订单查询
     * @Author: Steven
     * @Date: 2019/4/9
     */

    public AppResponse getOrders(OrderVo orderVo);/*订单查询*/
    /**
     * @ClassName: OrderService
     * @Description:订单详情
     * @Author: Steven
     * @Date: 2019/4/9
     */

    public AppResponse getOrderDetail(String orderId);/*订单详情*/
    /**
     * @ClassName: OrderService
     * @Description:订单发货
     * @Author: Steven
     * @Date: 2019/4/9
     */

    public AppResponse updateOrderStatus(OrderChangeStatusVo vo); /*订单发货*/
}
