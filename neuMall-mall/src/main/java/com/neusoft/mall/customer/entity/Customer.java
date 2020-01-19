package com.neusoft.mall.customer.entity;

import com.neusoft.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Customer
 * @Description:  普通实体
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@Data
public class Customer extends BaseEntity implements Serializable {
    private String customerId;
    private String customerNumber;
    private String customerPassword;
    private String customerName;
    private int customerIsUsed;
    private String customerNewPassword;
    private String TokenFront;
    private String TokenBackend;
}