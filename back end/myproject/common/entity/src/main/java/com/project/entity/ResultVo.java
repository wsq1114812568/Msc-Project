package com.project.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResultVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "competitionname")
    private String competitionname;

    @ApiModelProperty(value = "competitionid")
    private Long competitionid;

    @ApiModelProperty(value = "result")
    private String result;

    @ApiModelProperty(value = "username")
    private String username;

}
