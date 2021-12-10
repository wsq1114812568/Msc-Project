package com.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "attendCompetition")
@TableName("attend")
public class AttendEntity {

    @ApiModelProperty(value = "userid")
    @TableField("userid")
    private Long userid;

    @ApiModelProperty(value = "competitionid")
    @TableField("competitionid")
    private Long competitionid;

    @ApiModelProperty(value = "result(1:win，0:lose)")
    @TableField("result")
    private Integer result;

    @ApiModelProperty(value = "competitionName")
    @TableField(exist=false)
    private String competitionName;

    @ApiModelProperty(value = "UserName")
    @TableField(exist=false)
    private String userName;





}
