package com.neusoft.mall.homePage.service;

import com.neusoft.mall.category.entity.Category;
import com.neusoft.mall.goods.entity.Commodity;
import com.neusoft.mall.homePage.vo.RecommondCommodityVo;
import com.neusoft.common.entity.PageVo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HomePageService {
    /**
    * @Description:    获取推荐商品列表
    * @Author:         李瑞鑫
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    public PageVo<Commodity> getRecommondCommodityList(String commodityIsRecommend,RecommondCommodityVo recommondCommodityVo);
    /**
     * @Description:    获取购买过的商品
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public List<Commodity> getBuyCommodityList(String customerId);
    /**
    * @Description:    获取分类信息
    * @Author:         李瑞鑫
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    public List<Category> getClassifyList(String categoryParentId);
}
