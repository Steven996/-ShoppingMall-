package com.neusoft.mall.customer.entity;

import com.neusoft.common.entity.BasePageVo;
import lombok.Data;

/**
 * @ClassName: CustomerVo
 * @Description: 分页实体
 * @Author: hanjz
 * @Date: 2019/4/10
 */
@Data
public class CustomerVo extends BasePageVo {
    private String customerIsUsed;
    private String customerNumber;
    private String customerName;
    private String tokenBackend;
}