package com.djtu.permission.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 学生权限管理功能中的学生和对应角色的vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRoleVo implements Serializable {
    String id;
    String username;
    String name;
    String sex;
    String sno;
    String enterDate;
    String college;
    String stuClass;
    String schoolSys;
    List<String> perms;
    String remark;

}
