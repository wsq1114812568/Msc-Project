package com.project.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data

public class UserQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "userid")
    private String id;

    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "username")
    private String username;


}
