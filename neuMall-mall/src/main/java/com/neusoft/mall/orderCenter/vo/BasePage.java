package com.neusoft.mall.orderCenter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页参数")
public class BasePage {
    @ApiModelProperty(value = "分页查询页码数")
    private Integer pageNum ;

    @ApiModelProperty(value = "分页查询每页查询条数")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
