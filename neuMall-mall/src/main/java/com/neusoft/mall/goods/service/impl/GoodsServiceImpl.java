package com.neusoft.mall.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.common.entity.PageVo;
import com.neusoft.common.response.AppResponse;
import com.neusoft.common.util.StringUtil;
import com.neusoft.mall.goods.dao.GoodsDao;
import com.neusoft.mall.goods.entity.*;
import com.neusoft.mall.goods.service.GoodsService;
import com.neusoft.mall.util.FastDfsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GoodsServiceImpl
 * @Description:
 * @Author: shm12q
 * @Date: 2019/4/8
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Value("${fdfs.racker_server}")
    private String serverFdfs;

    @Override
    public AppResponse getGoodsList(CommodityVo commodityVo) {
        //查询所有商品
        PageVo pageVo = new PageVo();
        PageHelper.startPage(commodityVo.getPageNum(), commodityVo.getPageSize());
        List<CommodityDTO> goodsList = goodsDao.getGoodsList(commodityVo);
        pageVo.setList(goodsList);
        pageVo.setTotalRecords((int) new PageInfo<CommodityDTO>(goodsList).getTotal());
        return AppResponse.success("查询成功！", pageVo);
    }

    @Override
    public AppResponse updateGoodsIsSell(Commodity commodity) {
        AppResponse appResponse=AppResponse.success("修改成功");
        int result=goodsDao.updateGoodsIsSell(commodity);
        if (result == 0){
            appResponse=AppResponse.bizError("修改失败");
        }
        return appResponse;
    }

    @Override
    public AppResponse getGoodsDetail(Commodity commodity) {
        GoodsDetail goodsDetail = goodsDao.getGoodsDetail(commodity);
        List<CommodityPicDTO> pics=goodsDao.getGoodsPics(commodity.getCommodityId());
        if(null!=pics&&pics.size()!=0) {
            goodsDetail.setPictureList(pics);
        }
        return AppResponse.success("查询成功",goodsDetail);
    }

    @Override
    public AppResponse updateGoods(UpdateGoods goods) {
        goods.setCommodityIsLack(goods.getCommodityInventory() < 0 ? 1 : 0);
        goodsDao.deletePics(goods.getCommodityId());
        List<CommodityPicDTO> list=goods.getPictureList();
        if(null!=list&&list.size()!=0){
            for (int i = 0; i < list.size(); i++) {
                if (1==list.get(i).getPictureIsFistPicture()){
                    goods.setCommodityFirstPicture(list.get(i).getPictureAddress());
                }
                CommodityPic pic=new CommodityPic();
                pic.setPictureAddress(list.get(i).getPictureAddress());
                pic.setPictureIsFistPicture(list.get(i).getPictureIsFistPicture());
                pic.setCommodityId(goods.getCommodityId());
                pic.setPictureId(StringUtil.getUUID());
                pic.setCreatedBy(goods.getLastModifiedBy());
                pic.setLastModifiedBy(goods.getLastModifiedBy());
                goodsDao.addPic(pic);
            }
        }
        int result = goodsDao.updateGoods(goods);
        if (0 == result) {
            return AppResponse.bizError("修改失败，请重试！");
        }
        return AppResponse.success("修改成功！");
    }

    @Override
    public AppResponse addGoods(UpdateGoods goods) {
        goods.setCommodityId(StringUtil.getUUID());
        goods.setCommodityCode(StringUtil.initNo());
        goods.setCommodityIsLack(goods.getCommodityInventory() < 0 ? 1 : 0);
        goods.setCommodityTotalCount(0);
        List<CommodityPicDTO> list=goods.getPictureList();
        if(null!=list&&list.size()!=0){
            for (int i = 0; i < list.size(); i++) {
                if (1==list.get(i).getPictureIsFistPicture()){
                    goods.setCommodityFirstPicture(list.get(i).getPictureAddress());
                }
                CommodityPic pic=new CommodityPic();
                pic.setPictureAddress(list.get(i).getPictureAddress());
                pic.setPictureIsFistPicture(list.get(i).getPictureIsFistPicture());
                pic.setCommodityId(goods.getCommodityId());
                pic.setPictureId(StringUtil.getUUID());
                pic.setCreatedBy(goods.getCreatedBy());
                pic.setLastModifiedBy(goods.getCreatedBy());
                goodsDao.addPic(pic);
            }
        }

        int count=goodsDao.addGoods(goods);
        if (0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    @Override
    public AppResponse deleteGoods(Commodity commodity) {
        AppResponse appResponse = AppResponse.success("删除成功！");
        goodsDao.deletePics(commodity.getCommodityId());
        // 删除商品
        int count = goodsDao.deleteGoods(commodity);
        if (0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }


    @Override
    public AppResponse uploadFile(Map<String, Object> param) {
        AppResponse appResponse = null;
        try {
            MultipartFile multipartFile[] = (MultipartFile[]) param.get("files");
            for (int i = 0; i < multipartFile.length; i++) {
                FastDFSFile file = new FastDFSFile();
                file.setAuthor((String) param.get("userId"));
                String ext = multipartFile[i].getOriginalFilename().substring(multipartFile[i].getOriginalFilename().lastIndexOf(".") + 1);
                file.setContent(multipartFile[i].getBytes());
                file.setName(multipartFile[i].getOriginalFilename());
                file.setExt(ext);
                String filePath[] = FastDfsUtil.upload(file);
                log.error("shm"+filePath[0]+"--"+filePath[1]);
                appResponse=AppResponse.success("上传成功",serverFdfs + filePath[0] + "/" + filePath[1]);
            }
        } catch (Exception e) {
            appResponse=AppResponse.bizError("上传失败"+e);
        }
        return appResponse;
    }

    @Override
    public int deletePics(String commodityId) {
        List<CommodityPicDTO> list=goodsDao.getGoodsPics(commodityId);
        if (null!=list&&list.size()!=0){
            for (CommodityPicDTO bean :
                    list) { ;
                String[] url=bean.getPictureAddress().split("/",5);
                String groupName=url[3];
                String remoteFileName=url[4];
                try {
                    FastDfsUtil.deleteFile(groupName,remoteFileName);
                } catch (Exception e) {
                    log.error("删除图片失败"+e);
                    e.printStackTrace();
                }
            }
        }
        return goodsDao.deletePics(commodityId);
    }

}
