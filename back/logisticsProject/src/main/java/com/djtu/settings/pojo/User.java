package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String id;
  private String username;
  private String password;
  private String salt;
  private String name;
  private String sex;
  private String ident;
  private String identId;

}
