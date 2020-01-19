package com.neusoft.admin.menu.dao;

import com.neusoft.admin.menu.entity.Menu;
import com.neusoft.admin.menu.entity.MenuVO;

import java.util.List;

/**
 * @ClassName MenuDao
 * @Description 菜单管理
 * @Author xywang
 * @Date 2019/4/7
 */
public interface MenuDao {

    /**
     * 查询全部菜单
     * @return 菜单集合
     */
    List<MenuVO> listMenus();

    /**
     * 删除菜单
     * @param menu 菜单信息
     * @return
     */
    int deleteMenu(Menu menu);


    /**
     * 更新父级菜单类型为目录
     * @param menu 菜单信息
     * @return
     */
    int updateParentType(Menu menu);

    /**
     * 更新父级菜单类型为菜单
     * @param menu 菜单信息
     * @return
     */
    int updateParentTypes(Menu menu);
    int isExitChildMenu(Menu menu);

    String getMaxChildrenSortNo(String parentMenuCode);

    /**
     * 获取菜单范围代码
     * @param menu 菜单信息
     * @return
     */
    String getAuthCode(Menu menu);

    /**
     * 新增菜单
     * @param menu 菜单信息
     * @return
     */
    int insertMenu(Menu menu);

    /**
     * 修改菜单
     * @param menu 菜单信息
     * @return
     */
    int updateMenu(Menu menu);

}
