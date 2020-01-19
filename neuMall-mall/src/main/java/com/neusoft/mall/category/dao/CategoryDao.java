package com.neusoft.mall.category.dao;

import com.neusoft.mall.category.entity.Category;

import java.util.List;


public interface CategoryDao {

    /**
     * 查询全部分类
     * @return 分类集合
     */
    List<Category> getCategory();

    /**
     * 删除分类
     * @param category
     * @return
     */
    int deleteCategory(Category category);


    /**
     * 获取treecode递增值
     * @param category
     * @return
     */
    String getTreeCode(Category category);

    /**
     * 获取父级treecode
     * @param category
     * @return
     */
    String getParentTreeCode(Category category);

    /**
     * 获取父级level
     * @param category
     * @return
     */
    int getParentLevel(Category category);

    /**
     * 新增分类
     * @param category
     * @return
     */
    int addCategory(Category category);

    /**
     * 修改菜单
     * @param category
     * @return
     */
    int updateCategory(Category category);

    /**
     * 是否存在子分类
     * @param category
     * @return
     */
    int isExitChildCategory(Category category);
}
