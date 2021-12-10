package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "resultdetails")
@TableName("resultdetails")
public class ResultDetails {

    @ApiModelProperty(value = "detailid")
    @TableId(type = IdType.AUTO)
    private Long detailid;

    @ApiModelProperty(value = "competitionid")
    @TableField("competitionid")
    private Long competitionid;

    @TableField(exist=false)
    private String competitionname;

    @ApiModelProperty(value = "user1id")
    @TableField("user1id")
    private Long user1id;

    @TableField(exist=false)
    private String user1name;

    @ApiModelProperty(value = "user2id")
    @TableField("user2id")
    private Long user2id;

    @TableField(exist=false)
    private String user2name;

    @ApiModelProperty(value = "score1")
    @TableField("score1")
    private Long score1;

    @ApiModelProperty(value = "score2")
    @TableField("score2")
    private Long score2;





}
