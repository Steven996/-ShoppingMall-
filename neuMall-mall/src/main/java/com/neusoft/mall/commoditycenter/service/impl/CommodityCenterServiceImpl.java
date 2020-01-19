package com.neusoft.mall.commoditycenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.common.entity.PageVo;
import com.neusoft.common.response.AppResponse;
import com.neusoft.common.util.StringUtil;
import com.neusoft.common.util.UUIDUtil;
import com.neusoft.mall.commoditycenter.dao.CommodityCenterDao;
import com.neusoft.mall.commoditycenter.entity.*;
import com.neusoft.mall.commoditycenter.service.CommodityCenterService;
import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.goods.entity.Commodity;
import com.neusoft.mall.goods.entity.CommodityPic;
import com.neusoft.mall.shoppingCart.entity.Shop;
import com.neusoft.mall.orderCenter.entity.Order;
import com.neusoft.mall.orderCenter.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @ClassName: CommodityCenterServiceImpl
 * @Description: 调dao层
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class CommodityCenterServiceImpl implements CommodityCenterService {
    @Resource
    private CommodityCenterDao commodityCenterDao;
    @Autowired
    private RedisTemplate redisTemplate;
/**
 * @Dept：南京软件研发中心
 * @Description： 添加购物车貌似没有失败条件 除非未登录
 * @Author：hanjz
 * @Date: 2019/4/11
 * @Param： 自建实体
 * @Return： AppResponse
 */
    @Override
    public AppResponse addShoppingCart(ShopDto shopDto) {
        Customer customerOwn=(Customer)redisTemplate.opsForValue().get(shopDto.getTokenFront());
        Shop shop =new Shop();
        shop.setShopId(UUIDUtil.uuidStr());
        shop.setCommodityId(shopDto.getCommodityId());
        shop.setCustomerId(customerOwn.getCustomerId());
        shop.setShopNumber(shopDto.getShopNumber());
        shop.setCreatedBy(customerOwn.getCustomerId());
        shop.setLastModifiedBy(customerOwn.getCustomerId());
        shop.setIsDeleted(0);
        shop.setSortNo(0);
        shop.setVersion(0);
        if(commodityCenterDao.addShoppingCart(shop)==0){
            return AppResponse.bizError("加入购物车失败：未知错误");
        }
        return AppResponse.success("添加购物车成功");
    }
/**
 * @Dept：南京软件研发中心
 * @Description： 提交订单逻辑
 * @Author：hanjz
 * @Date: 2019/4/11
 * @Param： 自创实体
 * @Return：
 */
    @Override
    public AppResponse addOrder(OrderDto orderDto) {
        Customer customerOwn=(Customer)redisTemplate.opsForValue().get(orderDto.getTokenFront());
        //订单表
        List<OrderDto2> list = orderDto.getCommodityList();
        BigDecimal num = new BigDecimal(0);
        for (int i = 0; i < list.size(); i++) {
            BigDecimal bigDecimal1 = new BigDecimal(list.get(i).getCommodityNum());
            BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(commodityCenterDao.getPrice(list.get(i).getCommodityId()).getCommodityRetailPrice()));
            num = num.add(bigDecimal1.multiply(bigDecimal2));
        }
        OrderDto3 order3 = new OrderDto3();
        order3.setOrderNumber(StringUtil.initNo());
        order3.setOrderPrice(num.toString());
        Order order = new Order();
        order.setOrderId(UUIDUtil.uuidStr());
        order.setOrderNumber(StringUtil.initNo());
        order.setOrderPrice(num);
        order.setOrderState(1);
        order.setCustomerId(customerOwn.getCustomerId());
        order.setOrderAddress(orderDto.getOrderAddress());
        order.setReceiveTel(orderDto.getReceiveTel());
        order.setReceiveContact(orderDto.getReceiveContact());
        order.setOrderRemark(orderDto.getOrderRemark());
        order.setOrderDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        order.setCreatedBy(customerOwn.getCustomerId());
        order.setLastModifiedBy(customerOwn.getCustomerId());
        order.setIsDeleted(0);
        order.setSortNo(0);
        order.setVersion(0);
        if (commodityCenterDao.addOrder(order) == 0) {
            return AppResponse.bizError("下订单失败");
        }
        //订单明细表
        for (int i = 0; i < list.size(); i++) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderDetailId(UUIDUtil.uuidStr());
            orderDetail.setOrderId(order.getOrderId());
            orderDetail.setCommodityId(orderDto.getCommodityList().get(i).getCommodityId());
            orderDetail.setCommodityNum(Integer.parseInt(list.get(i).getCommodityNum()));
            BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(commodityCenterDao.getPrice(list.get(i).getCommodityId()).getCommodityRetailPrice()));
            orderDetail.setCommodityPrice(bigDecimal2);
            orderDetail.setCommodityTotalPrice(num);
            orderDetail.setCreatedBy(customerOwn.getCustomerId());
            orderDetail.setLastModifiedBy(customerOwn.getCustomerId());
            orderDetail.setIsDeleted(0);
            orderDetail.setSortNo(0);
            orderDetail.setVersion(0);
            if (commodityCenterDao.addOrderDetail(orderDetail) == 0) {
                return AppResponse.bizError("提交订单失败");
            }
        }
        order3.setOrderId(order.getOrderId());
        //响应信息：订单编号 支付金额 订单id
        return AppResponse.success("提交订单成功",order3);

    }
/**
 * @Dept：南京软件研发中心
 * @Description： 立即购买
 * @Author：hanjz
 * @Date: 2019/4/13
 * @Param： orderDetail
 * @Return： AppResponse
 */
    @Override
    public AppResponse getCommodityBuyNow(OrderDetail orderDetail) {
            List<OrderDto2> list = orderDetail.getCommodityList();
            ByNowVo byNowVo = new ByNowVo();
            BigDecimal num =new BigDecimal(0);
            List<CommodityList> commodityList1 =new ArrayList<>();
            for(int i=0;i<list.size();i++){
                Commodity commodity = commodityCenterDao.getPrice(list.get(i).getCommodityId());
                CommodityPic commodityPic=commodityCenterDao.getPictureAddress(list.get(i).getCommodityId());
                CommodityList commodityList = new CommodityList();
                BigDecimal bigDecimal1 = new BigDecimal(list.get(i).getCommodityNum());
                BigDecimal bigDecimal2 = new BigDecimal(commodity.getCommodityRetailPrice().toString());
                commodityList.setCommodityId(list.get(i).getCommodityId());
                commodityList.setCommodityCode(commodity.getCommodityCode());
                commodityList.setPictureAddress(commodityPic.getPictureAddress());
                commodityList.setCommodityName(commodity.getCommodityName());
                commodityList.setCommodityPrice(commodity.getCommodityRetailPrice().toString());
                commodityList.setCommodityNum(list.get(i).getCommodityNum());
                commodityList.setCommodityTotalPrice(bigDecimal2.multiply(bigDecimal1).toString());
                commodityList.setVersion(""+commodity.getVersion());
                commodityList1.add(commodityList);
                byNowVo.setCommodityList(commodityList1);
                num = num.add(bigDecimal2.multiply(bigDecimal1));
            }
            byNowVo.setTotalPrice(num.toString());
            return AppResponse.success("下单信息如下",byNowVo);
    }
/**
 * @Dept：南京软件研发中心
 * @Description： 查询交易记录
 * @Author：hanjz
 * @Date: 2019/4/13
 * @Param： commodityId
 * @Return： AppResponse
 */
    @Override
    public AppResponse getCommodityCenterTrading(String commodityId){
        if(commodityCenterDao.getCommodityCenterTrading(commodityId).isEmpty()){
            return AppResponse.notFound("没有交易记录");
        }
            return AppResponse.success("查询交易记录成功",commodityCenterDao.getCommodityCenterTrading(commodityId));
    }
/**
 * @Dept：南京软件研发中心
 * @Description： categoryFirst
 * @Author：hanjz
 * @Date: 2019/4/13
 * @Param：null
 * @Return： AppResponse
 */
    @Override
    public AppResponse getCommodityCenterSimilar(String categoryFirst) {
        if(commodityCenterDao.getCommodityCenterSimilar(categoryFirst)!=null){
        return AppResponse.success("同类商品推荐成功",commodityCenterDao.getCommodityCenterSimilar(categoryFirst));
    }
    return AppResponse.bizError("未有该一级分类id");
    }
/**
 * @Dept：南京软件研发中心
 * @Description：
 * @Author：Steven
 * @Date: 2019/4/22
 * @Param：commodityVO
 * @Return：com.neusoft.common.response.AppResponse
 */
    @Override
    public AppResponse getCommodityList(@RequestBody CommodityVO commodityVO) {
        PageVo pageVo = new PageVo();
        PageHelper.startPage(commodityVO.getPageNum(), commodityVO.getPageSize());
        List<CommodityInfo> commodityList = commodityCenterDao.getCommodityList(commodityVO);
        pageVo.setList(commodityList);
        pageVo.setTotalRecords((int) new PageInfo<>(commodityList).getTotal());
        if (0 == commodityList.size()){
            return AppResponse.notFound("商品中心主界面查询失败");
        }
            return AppResponse.success("商品中心主界面查询成功",pageVo);
    }
/**
 * @Dept：南京软件研发中心
 * @Description：
 * @Author：Steven
 * @Date: 2019/4/22
 * @Param：commodityDetailIn
 * @Return：com.neusoft.common.response.AppResponse
 */
    @Override
    public AppResponse getCommodityCenterDeatil(CommodityDetailIn commodityDetailIn){
//        Customer customer = (Customer)redisTemplate.opsForValue().get(commodityDetailIn.getTokenFront());
//        if (null != customer){
//            commodityDetailIn.setCustomerId(customer.getCustomerId());
//        }
       log.info(commodityDetailIn.getTokenFront());
        if(null != commodityDetailIn.getTokenFront()){
            Customer customer = (Customer)redisTemplate.opsForValue().get(commodityDetailIn.getTokenFront());
            commodityDetailIn.setCustomerId(customer.getCustomerId());
            CommodityDetail commodityDetail1 = commodityCenterDao.getCommodityCenterDeatil(commodityDetailIn);
            if (null == commodityDetail1){
                return AppResponse.notFound("商品详情查询失败");
            }
            commodityDetail1.setPictureList( commodityCenterDao.getCommodityCentPic(commodityDetailIn));
            return AppResponse.success("商品详情查询成功",commodityDetail1);
        }else{
            CommodityDetail commodityDetail1 = commodityCenterDao.getCommodityCenterDeatil(commodityDetailIn);
            if (null == commodityDetail1){
                return AppResponse.notFound("商品详情查询失败");
            }
            commodityDetail1.setPictureList( commodityCenterDao.getCommodityCentPic(commodityDetailIn));
            return AppResponse.success("商品详情查询成功",commodityDetail1);
        }
//        CommodityDetail commodityDetail1 = commodityCenterDao.getCommodityCenterDeatil(commodityDetailIn);
//        if (null == commodityDetail1){
//            return AppResponse.notFound("商品详情查询失败");
//        }
//        commodityDetail1.setPictureList( commodityCenterDao.getCommodityCentPic(commodityDetailIn));
//
//        return AppResponse.success("商品详情查询成功",commodityDetail1);
    }
/**
 * @Dept：南京软件研发中心
 * @Description：
 * @Author：Steven
 * @Date: 2019/4/22
 * @Param：commodityCollectPro
 * @Return：com.neusoft.common.response.AppResponse
 */
    @Override
    public AppResponse commodityCollection(CommodityCollectPro commodityCollectPro) {
        Customer customer=(Customer)redisTemplate.opsForValue().get(commodityCollectPro.getTokenFront());
        for (int i=0;i<commodityCollectPro.getCollectionList().size();i++){
            CommodityCollect cct = commodityCollectPro.getCollectionList().get(i);
            cct.setCollectId(UUIDUtil.uuidStr());
            cct.setCreatedBy(customer.getCustomerId());
            if("1".equals(cct.getCollectFlag())){
                int j = commodityCenterDao.commodityCollection(cct);
                if(0 == j){
                    return AppResponse.notFound("添加收藏失败");
                }
                return AppResponse.success("添加收藏成功");
            }else if ("0".equals(cct.getCollectFlag())){
                int j = commodityCenterDao.commodityUnCollection(cct);
                if(0 == j){
                    return AppResponse.notFound("取消收藏失败");
                    }
                }
        }
          return AppResponse.success("取消收藏成功");
    }
    /**
     * @Dept：南京软件研发中心
     * @Description：
     * @Author：Steven
     * @Date: 2019/4/22
     * @Param：null
     * @Return：
     */
    @Override
    public AppResponse commodityCollectionList(CommodityInfo commodityInfo) {
        Customer customer=(Customer)redisTemplate.opsForValue().get(commodityInfo.getTokenFront());

        commodityInfo.setCustomerId(customer.getCustomerId());

        List<CommodityInfo> commodityInfoList = commodityCenterDao.commodityCollectionList(commodityInfo);
        if (0 == commodityInfoList.size()){
            return AppResponse.notFound("查看收藏列表失败");
        }
        return AppResponse.success("查看收藏列表成功",commodityInfoList);
    }
}
