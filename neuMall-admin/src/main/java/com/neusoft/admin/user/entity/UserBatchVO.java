package com.neusoft.admin.user.entity;

import com.neusoft.common.entity.BaseEntity;
import com.neusoft.common.entity.BasePageVo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: UserBatchVO
 * @Description: 用户批量操作
 * @Author: shengtt
 * @Date: 2019/4/2
 */
@Data
public class UserBatchVO extends BaseEntity {

    private List<String> userList;//用户id列表
    private String tokenBackend;
}
