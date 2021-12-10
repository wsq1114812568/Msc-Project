package com.project.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CompetitionQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "competitionid")
    private String id;

    @ApiModelProperty(value = "competitionname")
    private String competitionname;

    @ApiModelProperty(value = "clubid")
    private String clubid;

    @ApiModelProperty(value = "type")
    private String type;
}
