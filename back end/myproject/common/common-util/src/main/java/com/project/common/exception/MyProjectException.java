package com.project.common.exception;

import com.project.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Custom global exception classes")
public class MyProjectException extends RuntimeException {
    @ApiModelProperty(value = "error code")
    private Integer code;

    public MyProjectException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public MyProjectException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "ProjectException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
