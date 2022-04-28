package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
  private String id;
  private String username;
  private String password;
  private String schoolSys;
  private String salt;
  private String name;
  private String sex;
  private String enterDate;
  private String college;
  private String stuClass;
  private String remark;
  private String sno;
  private Dorm dorm;
  private Tutor tutor;
  private String avatarPath;

}
