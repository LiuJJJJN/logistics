package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  private String id;
  private String sno;
  private String stuClass;
  private String enterDate;
  private String idcard;
  private String address;
  private String email;
  private String schoolSys;
  private String remark;
  private String dormId;
  private String college;
  private String tutorId;

}
