package com.neusoft.admin.menu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 部门：软件开发事业部
 * 描述：略
 * 作成者：xywang
 * 作成时间：2018/4/17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)//启用链式,如dept.setDname("xywang").setDb_source("test")
public class Menu {

    private String id;

    private String menuId;

    private String menuName;

    private String menuType;

    private String menuUrl;

    private String parentMenuId;

    private String menuComments;

    private String authCode;

    private String parentAuthCode;

    private String createBy;

    private Date gmtCreate;

    private String  lastModifiedBy;

    private Date gmtModified;

    private int sortNo;

    private int isDeleted;

    private int version;
}
