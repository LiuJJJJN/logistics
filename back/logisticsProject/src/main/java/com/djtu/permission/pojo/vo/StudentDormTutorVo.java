package com.djtu.permission.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDormTutorVo {
    @ExcelProperty("用户名")
    private String username;
    @ExcelProperty("学生姓名")
    private String name;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("入学时间")
    private String enterDate;
    @ExcelProperty("学制")
    private String schoolSys;
    @ExcelProperty("学院")
    private String college;
    @ExcelProperty("学生班级")
    private String stuClass;
    @ExcelProperty("学生备注")
    private String remark;
    @ExcelProperty("学号")
    private String sno;
    @ExcelProperty("导员姓名")
    private String tutorName;
    @ExcelProperty("学生所在寝室")
    private String doorNo;
}
