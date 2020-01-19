package com.neusoft.mall.order.dao;

import com.neusoft.mall.order.pojo.OrderChangeStatusVo;
import com.neusoft.mall.orderCenter.entity.Order;
import com.neusoft.mall.orderCenter.entity.OrderDetail;
import com.neusoft.mall.order.pojo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @ClassName: OrderDao
 * @Description:OrderDao
 * @Author: Steven
 * @Date: 2019/4/9
 */
@Mapper
@Component(value = "OrderDao")
public interface OrderDao {
    /**
     * @ClassName: OrderDao
     * @Description:订单查询
     * @Author: Steven
     * @Date: 2019/4/9
     */
    public List<Order> getOrders(OrderVo orderVo); /*订单查询*/
    /**
     * @ClassName: OrderDao
     * @Description:订单详情
     * @Author: Steven
     * @Date: 2019/4/9
     */
    public Order getOrderDetail(String orderId); /*订单详情*/
    public List<OrderDetail> getOrderDetailPro(String orderId);
    /**
     * @ClassName: OrderDao
     * @Description:订单发货
     * @Author: Steven
     * @Date: 2019/4/9
     */
    public int updateOrderStatus(OrderChangeStatusVo vo); /*订单发货*/
}
