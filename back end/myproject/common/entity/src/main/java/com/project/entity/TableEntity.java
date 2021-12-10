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
@ApiModel(description = "tables")
@TableName("tables")
public class TableEntity {
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "clubid")
    @TableField("clubid")
    private Long clubid;

    @TableField(exist=false)
    private String clubname;

    @ApiModelProperty(value = "number")
    @TableField("number")
    private Long number;

    @TableField(exist=false)
    private Long now;

    @TableField(exist=false)
    private Long lack;

    @ApiModelProperty(value = "day")
    @TableField("day")
    private String day;

    @ApiModelProperty(value = "begin")
    @TableField("begin")
    private String begin;

    @ApiModelProperty(value = "end")
    @TableField("end")
    private String end;




}
