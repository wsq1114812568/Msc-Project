package com.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "code")
@TableName("code")
public class Code {
    @ApiModelProperty(value = "id")
    @TableField("id")
    private String id;

    @ApiModelProperty(value = "code")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "phone")
    @TableField("phone")
    private String phone;



}
