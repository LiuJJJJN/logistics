package com.djtu.permission.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {
  private String id;
  private String code;
  private String name;
  private String fatherId;
  private String path;
  private String isMenu;
  private String roleId;

}
