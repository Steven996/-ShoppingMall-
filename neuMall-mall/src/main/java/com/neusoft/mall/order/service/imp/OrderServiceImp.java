package com.neusoft.mall.order.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.admin.accountAdmin.entity.SysUser;
import com.neusoft.common.entity.PageVo;
import com.neusoft.common.response.AppResponse;
import com.neusoft.common.response.AppResponseStatus;
import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.order.dao.OrderDao;
import com.neusoft.mall.order.pojo.OrderChangeStatusVo;
import com.neusoft.mall.order.service.OrderService;
import com.neusoft.mall.orderCenter.entity.Order;
import com.neusoft.mall.order.pojo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: OrderServiceImp
 * @Description:OrderServiceImp
 * @Author: Steven
 * @Date: 2019/4/9
 */
    @Transactional
    @Service
    @Slf4j
    public class OrderServiceImp implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * @ClassName: OrderServiceImp
     * @Description:订单查询
     * @Author: Steven
     * @Date: 2019/4/9
     */
    @Override/*订单查询*/
    public AppResponse getOrders(OrderVo orderVo){
        PageVo pageVo = new PageVo();
        PageHelper.startPage(orderVo.getPageNum(), orderVo.getPageSize());
        List<Order> orderList = orderDao.getOrders(orderVo);
        pageVo.setList(orderList);
        pageVo.setTotalRecords((int) new PageInfo<>(orderList).getTotal());
        if(null == orderList){
            return AppResponse.notFound("订单查询失败");
        }
        return AppResponse.success("订单查询成功",pageVo);
    }
    /**
     * @ClassName: OrderServiceImp
     * @Description:订单详情
     * @Author: Steven
     * @Date: 2019/4/9
     */
    @Override/*订单详情*/
    public AppResponse getOrderDetail(String orderId) {
        Order order = orderDao.getOrderDetail(orderId);
       if (null == order){
          return AppResponse.notFound("查询订单详情失败");
       }
            /*订单明细信息*/
        order.setCommodityList(orderDao.getOrderDetailPro(orderId));
       return AppResponse.success("查询订单详情成功",order);
    }
    /**
     * @ClassName: OrderServiceImp
     * @Description:订单发货
     * @Author: Steven
     * @Date: 2019/4/9
     */
    @Override/*订单发货*/
    public AppResponse updateOrderStatus(OrderChangeStatusVo vo) {
        SysUser sysUser = (SysUser)redisTemplate.opsForValue().get(vo.getTokenBackend());
        vo.setLastModifiedBy(sysUser.getUserAccount());
//      LOCK: vo.setLastModifiedBy("Steven");
        int count = orderDao.updateOrderStatus(vo);
        if(0 == count){
            return AppResponse.notFound("更改订单状态失败");
        }
        return AppResponse.success("更改订单状态成功",orderDao.updateOrderStatus(vo));
    }
}