package com.neusoft.mall.homePage.dao;

import com.neusoft.mall.category.entity.Category;
import com.neusoft.mall.goods.entity.Commodity;
import com.neusoft.mall.homePage.vo.RecommondCommodityVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface HomePageMapper {
    /**
    * @Description:    获取推荐的商品
    * @Author:         李瑞鑫
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    public List<Commodity> getRecommondCommodityList(@Param("commodityIsRecommend") String commodityIsRecommend,
                                                     RecommondCommodityVo recommondCommodityVo);
 /**w
  * @Description:    获取用户购买过的商品
  * @Author:         李瑞鑫
  * @UpdateRemark:   修改内容
  * @Version:        1.0
  */
    public List<Commodity> getBuyCommodityList(@Param("customerId") String customerId);
    /**
    * @Description:    获取分类信息
    * @Author:         李瑞鑫
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    public List<Category> getClassifyList(@Param("categoryParentId") String categoryParentId);

}
