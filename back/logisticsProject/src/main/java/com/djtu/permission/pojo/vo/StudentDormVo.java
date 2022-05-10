package com.djtu.permission.pojo.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
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
    @ExcelIgnore
    private String password;
    @ExcelIgnore
    private String salt;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("入学时间")
    private String enterDate;
    @ExcelProperty("学制")
    private String schoolSys;
    @ExcelProperty("学院")
    private String college;
    @ExcelProperty("班级")
    private String stuClass;
    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("学号")
    private String sno;
    @ExcelProperty("寝室号")
    private String doorNo;
    @ExcelIgnore
    private String tutorId;
    @ExcelIgnore
    private String id;
}
