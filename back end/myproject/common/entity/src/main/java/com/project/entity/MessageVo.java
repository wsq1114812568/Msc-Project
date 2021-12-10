package com.project.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MessageVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "friendid")
    private Long friendid;

    @ApiModelProperty(value = "content")
    private String content;


}
