package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "friend")
@TableName("friend")
public class FriendshipEntity {

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "userid")
    @TableField("userid")
    private Long userid;

    @ApiModelProperty(value = "friendid")
    @TableField("friendid")
    private Long friendid;

    @TableField(exist=false)
    private String friendname;

    @TableField(exist=false)
    private String friendphone;

    @ApiModelProperty(value = "status(1:agree，0:not agree)")
    @TableField("status")
    private Integer status;



}
