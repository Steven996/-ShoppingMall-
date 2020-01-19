package com.neusoft.admin.accountAdmin.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

/**
 * @ClassName: SysMenu
 * @Description:
 * @Author: cheng
 * @Date: 2019/4/9
 */
@Data
public class SysMenu extends BaseEntity { //数据库对应表：t_sys_menu
    private String menuId;
    private String menuName;
    private String menuType;
    private String menuUrl;
    private String parentMenuId;
    private String menuComments;
    private String authCode;
    private String id;
    private String tokenBackend;

}
