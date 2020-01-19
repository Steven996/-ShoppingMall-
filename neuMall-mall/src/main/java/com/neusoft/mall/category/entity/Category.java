package com.neusoft.mall.category.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: Category
 * @Description:
 * @Author: shm12q
 * @Date: 2019/4/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Category extends BaseEntity {

    private String categoryId;//分类id,UUID

    private String categoryParentId;//上级分类id

    private String categoryName;//分类名称

    private int categoryLevel;//1：一级；2：二级

    private String categoryRemark;//备注

    private String categoryTreeCode;//树状查询码

}
