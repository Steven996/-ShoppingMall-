package com.neusoft.mall.orderCenter.service;
import com.neusoft.mall.orderCenter.entity.GetOrderList;
import com.neusoft.mall.orderCenter.entity.Order;
import com.neusoft.mall.orderCenter.vo.OrderVo;
import com.neusoft.mall.orderCenter.vo.PageVo;

import java.util.List;
public interface OrderCenterService {
    /**
     * @Description:    分页获取不同条件的订单列表
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public PageVo<Order> getOrderList(GetOrderList getOrderList,String customerId, OrderVo orderVo);
    /**
     * @Description:    通过OrderId 获取订单详情
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public Order getOrderByOrderId(String orderId);
    /**
     * @Description:    更新订单状态
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public void updateOrderStatus(List<String> orderNumberList,String orderState,String customerId);
}
