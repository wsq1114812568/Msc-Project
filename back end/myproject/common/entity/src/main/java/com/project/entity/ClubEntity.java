package com.project.entity;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "club")
@TableName("club")
public class ClubEntity {

    @ApiModelProperty(value = "clubid")
    @TableId(type = IdType.AUTO)
    private Long clubid;

    @ApiModelProperty(value = "delete(1:deleted，0:undeleted)")
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "clubname")
    @TableField("clubname")
    private String clubname;

    @ApiModelProperty(value = "location")
    @TableField("location")
    private String location;

    @ApiModelProperty(value = "phone")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "email")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "website")
    @TableField("website")
    private String website;

    //Hierarchy display in the table
    @ApiModelProperty(value = "hasChildren")
    @TableField(exist=false)
    private boolean hasChildren=true;

}
