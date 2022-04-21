package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生详细信息表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;          //uuid
    private String sno;         //学号
    private String stu_class;   //班级
    private String enter_date;  //入学时间
    private String idcard;      //身份证号
    private String address;     //地址
    private String email;       //邮箱
    private String school_sys;  //学制
    private String remark;      //备注信息
    private String dorm_id;     //寝室id
    private String college;     //所属学院
    private String tutor_id;    //导员id

}
