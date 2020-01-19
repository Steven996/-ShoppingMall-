package com.neusoft.mall.category.service.impl;

import com.neusoft.common.response.AppResponse;
import com.neusoft.common.util.StringUtil;
import com.neusoft.mall.category.dao.CategoryDao;
import com.neusoft.mall.category.entity.Category;
import com.neusoft.mall.category.entity.Tree;
import com.neusoft.mall.category.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: CategoryServiceImpl
 * @Description:分类管理
 * @Author: shm12q
 * @Date: 2019/4/8
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryDao categoryDao;

    private String rootId="0";

    //添加分类
    @Override
    public AppResponse addCategory(Category category) {
        AppResponse appResponse = AppResponse.success("新增成功！");
        // 根菜单就是菜单代码，其他新建子菜单时，获取父菜单的范围代码+‘3位自增长数’。
        if(null == category.getCategoryParentId() || "".equals(category.getCategoryParentId()) ) {
            category.setCategoryLevel(1);
            category.setCategoryParentId(rootId);
            category.setCategoryTreeCode(categoryDao.getTreeCode(category));
        }else{
            // 获取菜单范围代码
            category.setCategoryTreeCode(categoryDao.getParentTreeCode(category)+categoryDao.getTreeCode(category));
            category.setCategoryLevel(categoryDao.getParentLevel(category)+1);
        }
        category.setCategoryId(StringUtil.getUUID());
        // 新增菜单
        int count = categoryDao.addCategory(category);
        if(0 == count) {
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        return appResponse;
    }

    //获取分类
    @Override
    public Tree getCategory() {
        List<Category> categoryList = categoryDao.getCategory();
        Tree rootTree = new Tree();
        initTree(rootTree, categoryList, rootId);
        return rootTree;
    }

    //建立返回树
    private void initTree(Tree rootTree, List<Category> allMenus, String selfCode){
        Iterator<Category> menuIterator = allMenus.iterator();
        while(menuIterator.hasNext()){
            Category temp = menuIterator.next();
            //初始化自身节点
            if(temp.getCategoryId().equals(selfCode)){
                categoryToTree(temp,rootTree);
            }else if(temp.getCategoryParentId().equals(selfCode)){
                //初始化子节点
                Tree children = new Tree();
                categoryToTree(temp,children);
                rootTree.getChildren().add(children);
                //递归
                initTree(children,allMenus,temp.getCategoryId());
            }
        }
    }

    //树节点转换
    private void categoryToTree(Category category, Tree tree){
        tree.setId(category.getCategoryId());
        tree.setCcpid(category.getCategoryParentId());
        tree.setLabel(category.getCategoryName());
        tree.setType(category.getCategoryLevel()+"");
        tree.setAttributes(category);
    }

    //修改分类
    @Override
    public AppResponse updateCategory(Category category) {
        AppResponse appResponse = AppResponse.success("修改成功！");
        int count = categoryDao.updateCategory(category);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    //删除分类
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse deleteCategory(Category category) {
        //查看是否含有子分类
        int num = categoryDao.isExitChildCategory(category);
        if(num>0){
            return AppResponse.bizError("删除失败,含有子分类");
        }
        else {
            categoryDao.deleteCategory(category);
            return AppResponse.success("删除成功");
        }
    }
}
