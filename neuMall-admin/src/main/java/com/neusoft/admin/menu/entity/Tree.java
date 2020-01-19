package com.neusoft.admin.menu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门：软件开发事业部
 * 描述：略
 * 作成者：xywang
 * 作成时间：2019/4/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Tree {

    private String id;

    private String label;

    private String index;

    private Object attributes;

    private List<Tree> children = new ArrayList<Tree>();

    private String pid;

    private String code;

    private String type;

}
