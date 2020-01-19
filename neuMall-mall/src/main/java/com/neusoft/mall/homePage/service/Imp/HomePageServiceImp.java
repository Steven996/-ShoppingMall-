package com.neusoft.mall.homePage.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.mall.category.entity.Category;
import com.neusoft.mall.goods.entity.Commodity;
import com.neusoft.mall.homePage.dao.HomePageMapper;
import com.neusoft.mall.homePage.service.HomePageService;
import com.neusoft.mall.homePage.vo.RecommondCommodityVo;
import com.neusoft.common.entity.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomePageServiceImp implements HomePageService {
    @Autowired
    HomePageMapper homePageMapper;
    /**
     * @Description:    获取推荐商品列表
     * @Author:         李瑞鑫
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    @Override
    public PageVo<Commodity> getRecommondCommodityList(String commodityIsRecommend ,RecommondCommodityVo recommondCommodityVo) {
        PageVo pageVo = new PageVo<>();
        PageHelper.startPage(recommondCommodityVo.getPageNum(),recommondCommodityVo.getPageSize());
        List<Commodity> commodityList =homePageMapper.getRecommondCommodityList(commodityIsRecommend,recommondCommodityVo);
        pageVo.setList(commodityList);
        pageVo.setTotalRecords((int)new PageInfo<Commodity>(commodityList).getTotal());
        return pageVo;
    }
    /**
    * @Description:    获取用户购买过的商品
    * @Author:         李瑞鑫
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @Override
    public List<Commodity> getBuyCommodityList(String customerId) {
        return homePageMapper.getBuyCommodityList(customerId);
    }
    /**
    * @Description:   根据上级分类Id查询分类信息
    * @Author:         李瑞鑫
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @Override
    public List<Category> getClassifyList(String categoryParentId) {
        return homePageMapper.getClassifyList(categoryParentId);
    }
}
