package com.djtu.permission.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDormVo {

    @ExcelProperty("用户名")
    private String username;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("学院")
    private String college;
    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("寝室号")
    private String doorNo;
}
