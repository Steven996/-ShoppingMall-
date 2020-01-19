package com.neusoft.common.entity;

import lombok.Data;

/**
 * @ClassName: BaseEntity
 * @Description:
 * @Author: shengtt
 * @Date: 2019/4/2
 */
@Data
public class BaseEntity {
    private String createdBy;//创建人
    private String gmtCreate;//创建时间
    private String lastModifiedBy;//更新人
    private String gmtModified;//更新时间
    private int isDeleted;//是否作废1表示作废，0表示未作废
    private int sortNo;//序号
    private int version;//版本号
    private String tokenFront;
    private String tokenBackend;
}
