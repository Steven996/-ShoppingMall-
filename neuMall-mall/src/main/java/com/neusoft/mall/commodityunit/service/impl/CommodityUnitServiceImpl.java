package com.neusoft.mall.commodityunit.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.admin.accountAdmin.entity.SysUser;
import com.neusoft.common.entity.PageVo;
import com.neusoft.common.response.AppResponse;
import com.neusoft.common.util.UUIDUtil;
import com.neusoft.mall.commodityunit.dao.CommodityUnitDao;
import com.neusoft.mall.commodityunit.entity.CommodityUnit;
import com.neusoft.mall.commodityunit.entity.CommodityUnitVo;
import com.neusoft.mall.commodityunit.service.CommodityUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CommodityUnitServiceImpl
 * @Description: 商品单位管理业务逻辑实现
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CommodityUnitServiceImpl implements CommodityUnitService {
    @Resource
    private CommodityUnitDao commodityUnitDao;
    @Autowired
    private RedisTemplate redisTemplate;
   /**
    * @Dept：南京软件研发中心
    * @Description： 调Dao层方法查询数据 在此层使用PageHelper分页
    * @Author：hanjz
    * @Date: 2019/4/9
    * @Param： unitName(可选) pageNum页码 pageSize条数 包装在实体中
    * @Return： PageVo 分页后页面实体
    */
    @Override
    public AppResponse getCommodityUnitListPage(CommodityUnitVo commodityUnitVo) {
        PageVo pageVo = new PageVo();
        PageHelper.startPage(commodityUnitVo.getPageNum(), commodityUnitVo.getPageSize());
        List<CommodityUnit> comList=commodityUnitDao.getCommodityUnitListPage(commodityUnitVo);
        if(comList.isEmpty()){
            return AppResponse.notFound("此单位名不存在");
        }
        pageVo.setList(comList);
        pageVo.setTotalRecords((int) new PageInfo<>(comList).getTotal());
        return AppResponse.success("查询商品单位列表分页成功",pageVo);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调Dao层方法查询数据
     * @Author：hanjz
     * @Date: 2019/4/9
     * @Param： null
     * @Return： Map
     */
    @Override
    public AppResponse getCommodityUnitList() {
        return AppResponse.success("查询商品单位列表成功",commodityUnitDao.getCommodityUnitList());
    }
/**
 * @Dept：南京软件研发中心
 * @Description： 调dao层增加商品单位
 * @Author：hanjz
 * @Date: 2019/4/10
 * @Param： null
 * @Return： Map
 */
    @Override
    public AppResponse addCommodityUnit(CommodityUnit commodityUnit) {
        SysUser userInfo = (SysUser) redisTemplate.opsForValue().get(commodityUnit.getTokenBackend()) ;
        if(!commodityUnitDao.getExistCommodityUnit(commodityUnit.getUnitName()).isEmpty()){
            return AppResponse.bizError("已存在该单位");
        }
        CommodityUnit commodityUnit1=new CommodityUnit();
        commodityUnit1.setUnitId(UUIDUtil.uuidStr());
        commodityUnit1.setUnitName(commodityUnit.getUnitName());
        commodityUnit1.setCreatedBy(userInfo.getUserUuid());
        commodityUnit1.setLastModifiedBy(userInfo.getUserUuid());
        commodityUnit1.setIsDeleted(0);
        commodityUnit1.setSortNo(0);
        commodityUnit1.setVersion(0);
        commodityUnitDao.addCommodityUnit(commodityUnit1);
        return AppResponse.success("添加商品单位成功");
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调dao层更新商品单位
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 商品单位id，商品单位名字（非必有），版本
     * @Return： Map
     */
    @Override
    public AppResponse updateCommodityUnit(CommodityUnit commodityUnit) {
        SysUser userInfo = (SysUser) redisTemplate.opsForValue().get(commodityUnit.getTokenBackend()) ;
        if(!commodityUnitDao.getExistCommodityUnit(commodityUnit.getUnitName()).isEmpty()){
            return AppResponse.bizError("已存在该单位");
        }
        if(commodityUnitDao.getCommodityUnitDetail(commodityUnit).isEmpty()){
            return   AppResponse.notFound("此id不存在");
        }
        commodityUnit.setLastModifiedBy(userInfo.getUserUuid());
        if(commodityUnitDao.updateCommodityUnit(commodityUnit)==0){
            return AppResponse.versionError("版本不一致");
        }
        commodityUnit.setVersion(commodityUnit.getVersion()+1);
        commodityUnitDao.addVersion(commodityUnit);
        return AppResponse.success("更新商品单位成功");
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调dao层 查询商品单位详情
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 商品单位id
     * @Return： Map
     */
    @Override
    public AppResponse getCommodityUnitDetail(CommodityUnit commodityUnit) {
        if(commodityUnitDao.getCommodityUnitDetail(commodityUnit).isEmpty()){
          return   AppResponse.notFound("此id不存在");
        }
       return AppResponse.success("查询商品单位详情成功",commodityUnitDao.getCommodityUnitDetail(commodityUnit));
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调dao层 删除商品单位
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 商品单位id
     * @Return： Map
     */
    @Override
    public AppResponse deleteCommodityUnit(CommodityUnit commodityUnit) {
        if(commodityUnitDao.deleteCommodityUnit(commodityUnit)==0){
            return AppResponse.notFound("此id不存在");
        }
        return AppResponse.success("删除商品单位成功");
    }
}