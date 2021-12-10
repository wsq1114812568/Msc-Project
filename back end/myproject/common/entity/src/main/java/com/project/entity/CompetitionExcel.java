package com.project.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CompetitionExcel {

    @ExcelProperty(value = "competitionid",index = 0)
    private Long competitionid;

    @ExcelProperty(value = "competitionname",index = 1)
    private String competitionname;

    @ExcelProperty(value = "date",index = 2)
    private String date;

    @ExcelProperty(value = "rule",index = 3)
    private Integer rule;

    @ExcelProperty(value = "clubid",index = 4)
    private Long clubid;

    @ExcelProperty(value = "number_of_people",index = 5)
    private Long number;

    @ExcelProperty(value = "status",index = 6)
    private Integer status;





}
