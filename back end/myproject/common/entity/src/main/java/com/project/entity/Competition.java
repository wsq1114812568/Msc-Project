package com.project.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "competition")
@TableName("competition")
public class Competition {

    @ApiModelProperty(value = "competitionid")
    @TableId(type = IdType.AUTO)
    private Long competitionid;

    @ApiModelProperty(value = "competitionname")
    @TableField("competitionname")
    private String competitionname;

    @ApiModelProperty(value = "date")
    @TableField("date")
    private String date;

    @ApiModelProperty(value = "type(0:men's singles,1:women's singles,2:men's doubles,3:women's doubles)")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "delete(1:deleted，0:undeleted)")
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "status(1:completed，0:uncompleted)")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "number_of_people_required")
    @TableField("number_of_people_required")
    private Long number;

    @TableField(exist=false)
    private Long now;
    @TableField(exist=false)
    private Long lack;


    @ApiModelProperty(value = "clubid")
    @TableField("clubid")
    private Long clubid;

    @TableField(exist=false)
    private String clubname;

    @TableField(exist=false)
    private String location;

    @ApiModelProperty(value = "winnerid")
    @TableField("winnerid")
    private Long winnerid;

    @TableField(exist=false)
    private String winnername;

    @ApiModelProperty(value = "locked")
    @TableField(exist=false)
    private boolean locked=true;




}
