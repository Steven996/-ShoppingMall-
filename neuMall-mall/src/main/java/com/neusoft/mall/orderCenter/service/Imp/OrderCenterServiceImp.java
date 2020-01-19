package com.neusoft.mall.orderCenter.service.Imp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.mall.orderCenter.dao.OrderCenterMapper;
import com.neusoft.mall.orderCenter.entity.GetOrderList;
import com.neusoft.mall.orderCenter.entity.Order;
import com.neusoft.mall.orderCenter.entity.OrderDetail;
import com.neusoft.mall.orderCenter.service.OrderCenterService;
import com.neusoft.mall.orderCenter.vo.OrderVo;
import com.neusoft.mall.orderCenter.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class OrderCenterServiceImp implements OrderCenterService {
    @Autowired
    OrderCenterMapper orderCenterMapper;
    /**
     * @Description:    分页获取不同条件的订单列表
     * @Author:         李瑞鑫
     * @UpdateRemark:   代码格式修改
     * @Version:        7.0
     * @Param: (包含订单编号、起始日期、结束日期、订单状态的实体)、（用户的ID）、（分页信息）
     */
    @Override
    public PageVo<Order> getOrderList(GetOrderList getOrderList,String customerId, OrderVo orderVo)
    {
        //处理分页信息
        PageVo pagevo = new PageVo<>();
        List<Order> orderlist = new ArrayList<>();
        PageHelper.startPage(orderVo.getPageNum(),orderVo.getPageSize());
        String orderNumber = getOrderList.getOrderNumber();
        String orderDateStart = getOrderList.getOrderDateStart();
        String orderDateEnd = getOrderList.getOrderDateEnd();
        String orderState = getOrderList.getOrderState();

        if(orderNumber!=null){
            log.info("massage:{}","正在根据订单信息查找订单");
            Order order = orderCenterMapper.getSingleOrder(orderNumber,customerId);
            List<OrderDetail> commodityList = orderCenterMapper.getCommodityList(order.getOrderId());
            order.setCommodityList(commodityList);
            orderlist.add(order);
            pagevo.setList(orderlist);
            pagevo.setTotalRecords((int)new PageInfo<Order>(orderlist).getTotal());
            return pagevo;
        }
        if((orderDateStart!=null||orderDateEnd!=null)){
                if(orderDateStart==null&&orderDateEnd!=null){
                    orderDateStart = "0000-01-01";
                }
                else if(orderDateStart!=null&&orderDateEnd==null){
                    orderDateEnd = "9999-12-01";
                }
                orderDateEnd = orderDateEnd+" 23:59:59";
                if(orderState==null){
                    orderlist = orderCenterMapper.getOrderListByDate(customerId,orderDateStart,orderDateEnd,orderVo);
                }
                else{
                    orderlist= orderCenterMapper.getOrderListByDateAndState(customerId,orderDateStart,orderDateEnd,orderState,orderVo);
                }
                orderlist=getlist(orderlist);
                pagevo.setList(orderlist);
                pagevo.setTotalRecords((int)new PageInfo<Order>(orderlist).getTotal());
                return pagevo;
        }
        if(orderState!=null){
            orderlist= orderCenterMapper.getOrderListByState(customerId,orderState,orderVo);
            System.out.println(orderlist+"这里是orderlist");
            orderlist=getlist(orderlist);
            pagevo.setList(orderlist);
            pagevo.setTotalRecords((int)new PageInfo<Order>(orderlist).getTotal());
            return pagevo;
        }
        orderlist = orderCenterMapper.getOrderList(orderVo,customerId);
        orderlist=getlist(orderlist);
        pagevo.setList(orderlist);
        pagevo.setTotalRecords((int)new PageInfo<Order>(orderlist).getTotal());
        return pagevo;
    }
    /**
    * @Description:    通过OrderId 获取订单详情
    * @Author:         李瑞鑫
    * @Version:        1.0
     * @Param:        (订单ID)
    */
    @Override
    public Order getOrderByOrderId(String orderId) {
        Order order =  orderCenterMapper.getSingleOrderByOrderId(orderId);
        List<OrderDetail> commodityList = orderCenterMapper.getCommodityList(order.getOrderId());
        order.setCommodityList(commodityList);
        return order;
    }
    /**
    * @Description:    更新订单状态
    * @Author:         李瑞鑫
    * @UpdateRemark:   无
    * @Version:        1.0
     * @Param:         (订单编号列表)、（新订单状态）、（用户ID）
    */
    @Override
    public void updateOrderStatus(List<String> orderNumberList, String orderState,String customerId) {
        orderCenterMapper.updateOrderStatus(orderNumberList,orderState,customerId);
    }
    /**
    * @Description:    循环将商品列表塞进订单表中；复用方法
    * @Author:         李瑞鑫
    */
    public List<Order> getlist(List<Order> orderlist){
        for (int i=0;i<orderlist.size();i++) {
            List<OrderDetail> commodityList = orderCenterMapper.getCommodityList(orderlist.get(i).getOrderId());
            orderlist.get(i).setCommodityList(commodityList);
        }
        return orderlist;
    }
}
