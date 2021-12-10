package com.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("message")
public class MessageEntity extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "userid")
    @TableField("userid")
    private Long userid;

    @TableField(exist=false)
    private String username;

    @ApiModelProperty(value = "friendid")
    @TableField("friendid")
    private Long friendid;

    @TableField(exist=false)
    private String friendname;

    @ApiModelProperty(value = "content")
    @TableField("content")
    private String content;


}
