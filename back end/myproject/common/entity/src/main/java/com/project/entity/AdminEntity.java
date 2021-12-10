package com.project.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "administrator")
@TableName("administrator")
public class AdminEntity extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "adminname")
    @TableField("adminname")
    private String adminname;

    @ApiModelProperty(value = "password")
    @TableField("password")
    private String password;


}
