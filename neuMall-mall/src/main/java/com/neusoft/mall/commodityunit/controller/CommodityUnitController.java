package com.neusoft.mall.commodityunit.controller;


import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.commodityunit.entity.CommodityUnit;
import com.neusoft.mall.commodityunit.entity.CommodityUnitVo;
import com.neusoft.mall.commodityunit.service.impl.CommodityUnitServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @ClassName: CommodityUnitController
 * @Description: 商品单位管理控制器
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@RestController
@Slf4j
@RequestMapping("/backend/commodityUnit")
@Api("商品单位")
public class CommodityUnitController {

    @Autowired
    private CommodityUnitServiceImpl commodityUnitServiceImpl;
    /**
    * @Dept：南京软件研发中心
    * @Description： 调用业务层方法 实现将查询出的单位列表分页
    * @Author：hanjz
    * @Date: 2019/4/9
    * @Param： unitName(可选) pageNum页码 pageSize条数
    * @Return： PageVo 分页后页面实体
    */
    @ApiOperation(value ="商品单位查询")
    @GetMapping("getCommodityUnitListPage")
    public AppResponse getCommodityUnitListPage(CommodityUnitVo commodityUnitVo){
        return commodityUnitServiceImpl.getCommodityUnitListPage(commodityUnitVo);

    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调用业务层方法 查询出无分页单位列表
     * @Author：hanjz
     * @Date: 2019/4/9
     * @Param： null
     * @Return： Map
     */
    @ApiOperation(value ="商品单位查询（无分页）")
    @GetMapping("getCommodityUnitList")
    public AppResponse getCommodityUnitList(){
        return commodityUnitServiceImpl.getCommodityUnitList();
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调用业务层方法 查询出无分页单位列表
     * @Author：hanjz
     * @Date: 2019/4/9
     * @Param： null
     * @Return： Map
     */
    @ApiOperation(value ="新增商品单位")
    @PostMapping("addCommodityUnit")
    public AppResponse addCommodityUnit(CommodityUnit commodityUnit){
        return commodityUnitServiceImpl.addCommodityUnit(commodityUnit);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调用业务层方法 更新商品单位信息
     * @Author：hanjz
     * @Date: 2019/4/9
     * @Param： 单位名称（非必选），单位id，版本号
     * @Return： Map
     */
    @ApiOperation(value ="更新商品单位")
    @PutMapping("updateCommodityUnit")
    public AppResponse updateCommodityUnit(CommodityUnit commodityUnit){
        return commodityUnitServiceImpl.updateCommodityUnit(commodityUnit);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调用业务层方法 查询商品单位详情
     * @Author：hanjz
     * @Date: 2019/4/9
     * @Param： 单位id
     * @Return： Map
     */
    @ApiOperation(value ="查询商品单位详情")
    @GetMapping("getCommodityUnitDetail")
    public AppResponse getCommodityUnitDetail(CommodityUnit commodityUnit){
        return commodityUnitServiceImpl.getCommodityUnitDetail(commodityUnit);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调用业务层方法 删除商品单位
     * @Author：hanjz
     * @Date: 2019/4/9
     * @Param： 单位id
     * @Return： Map
     */
    @ApiOperation(value ="商品单位删除")
    @PutMapping("deleteCommodityUnit")
    public AppResponse deleteCommodityUnit(CommodityUnit commodityUnit){
        return commodityUnitServiceImpl.deleteCommodityUnit(commodityUnit);
    }
}
