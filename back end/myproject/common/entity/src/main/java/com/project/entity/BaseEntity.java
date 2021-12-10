package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "createtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("createtime")
    private Date createTime;

    @ApiModelProperty(value = "updatetime")
    @TableField("updatetime")
    private Date updateTime;

    @ApiModelProperty(value = "delete(1:deleted，0:undeleted)")
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "others")
    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();
}
