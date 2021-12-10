package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "booktables")
@TableName("booktables")
public class BookTables {
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "clubid")
    @TableField("clubid")
    private Long clubid;

    @TableField(exist=false)
    private String clubname;

    @ApiModelProperty(value = "userid")
    @TableField("userid")
    private Long userid;

    @TableField(exist=false)
    private String username;

    @ApiModelProperty(value = "begin")
    @TableField("begin")
    private String begin;

    @ApiModelProperty(value = "end")
    @TableField("end")
    private String end;

    @ApiModelProperty(value = "day")
    @TableField("day")
    private String day;

    @ApiModelProperty(value = "status")//0:doesn't use the table    1:use the table
    @TableField("status")
    private Integer status;


}
