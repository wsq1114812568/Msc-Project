package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "result")
@TableName("result")
public class ResultEntity {

    @ApiModelProperty(value = "resultid")
    @TableId(type = IdType.AUTO)
    private Long resultid;

    @ApiModelProperty(value = "competitionid")
    @TableField("competitionid")
    private Long competitionid;

    @ApiModelProperty(value = "firstid")
    @TableField("firstid")
    private Long firstid;

    @TableField(exist=false)
    private String firstname;

    @ApiModelProperty(value = "secondid")
    @TableField("secondid")
    private Long secondid;

    @TableField(exist=false)
    private String secondname;

    @ApiModelProperty(value = "thirdid")
    @TableField("thirdid")
    private Long thirdid;

    @TableField(exist=false)
    private String thirdname;





}
