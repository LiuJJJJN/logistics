package com.djtu.settings.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.djtu.dorm.pojo.Dorm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
  @ExcelIgnore
  private String id;
  @ExcelIgnore
  private String username;
  @ExcelIgnore
  private String password;
  @ExcelIgnore
  private String salt;
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
  @ExcelIgnore
  private Dorm dorm;
  @ExcelIgnore
  private Tutor tutor;
  @ExcelIgnore
  private String avatarPath;

}
