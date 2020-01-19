package com.neusoft.mall.orderCenter.dao;

import com.neusoft.mall.orderCenter.entity.Order;
import com.neusoft.mall.orderCenter.entity.OrderDetail;
import com.neusoft.mall.orderCenter.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
@Mapper
public interface OrderCenterMapper {
    /**
     * @Description:    获取订单列表
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     *
     */
    public List<Order> getOrderList(OrderVo orderVo,@Param("customerid") String customerId);
    /**
     * @Description:    根据日期获取订单列表
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public List<Order> getOrderListByDate(@Param("customerid") String customerId,
                                          @Param("orderDateStart")String orderDateStart,
                                          @Param("orderDateEnd")String orderDateEnd,
                                          OrderVo orderVo);
    /**
    * @Description:    根据订单编号获取单个订单信息
    * @Author:         李瑞鑫
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    public Order getSingleOrder(@Param("orderNumber") String orderNumber,@Param("customerId")String customerId);
    /**
     * @Description:    根据订单ID查询订单详情
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public Order getSingleOrderByOrderId(@Param("orderId")String orderId);
    /**
     * @Description:    根据订单ID查询订单明细
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public List<OrderDetail> getCommodityList(@Param("orderId")String orderId);
    /**
     * @Description:    同时根据订单日期和状态查询订单列表
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public List<Order> getOrderListByDateAndState(@Param("customerid") String customerId,
                                                  @Param("orderDateStart")String orderDateStart,
                                                  @Param("orderDateEnd")String orderDateEnd,
                                                  @Param("orderState")String orderState,
                                                  OrderVo orderVo);
    /**
     * @Description:    根据订单状态查询订单列表
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public List<Order> getOrderListByState(@Param("customerid") String customerId,
                                           @Param("orderState")String orderState,
                                           OrderVo orderVo);
    /**
     * @Description:    更新一个订单列表的所有订单的状态
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public int updateOrderStatus(@Param("orderNumberList")List<String> orderNumberList,
                                  @Param("orderState")String orderState,
                                  @Param("customerId")String customerId);
}
