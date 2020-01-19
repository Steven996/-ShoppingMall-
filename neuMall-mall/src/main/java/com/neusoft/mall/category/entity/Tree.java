package com.neusoft.mall.category.entity;

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

    private Object attributes;

    private String ccpid;

    private String type;

    private List<Tree> children = new ArrayList<Tree>();

}
