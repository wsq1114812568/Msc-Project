package com.project.entity;

import com.project.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(description = "user")
@TableName("user")
public class UserEntity extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "username")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "phone")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "password")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "gender")//0 male, 1 female
    @TableField("gender")
    private Integer gender;

}



