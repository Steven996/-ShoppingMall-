package com.neusoft.mall.orderCenter.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel(description = "分页")
@Data
public class PageVo<T> {

    private List<T> list;

    private Integer totalRecords;


}
