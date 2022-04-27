package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
  private String id;
  private String username;
  private String password;
  private String salt;
  private String name;
  private String sex;
  private String college;
  private Dorm dorm;
  private String remark;
  private String avatarPath;

}
