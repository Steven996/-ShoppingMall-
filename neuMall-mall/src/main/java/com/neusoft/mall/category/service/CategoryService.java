package com.neusoft.mall.category.service;

import com.neusoft.common.response.AppResponse;
import com.neusoft.mall.category.entity.Category;
import com.neusoft.mall.category.entity.Tree;


public interface CategoryService {

    //新增分类
    AppResponse addCategory(Category category);

    //获取分类
    Tree getCategory();

    //修改分类
    AppResponse updateCategory(Category category);

    //删除分类
    AppResponse deleteCategory(Category category);
}
