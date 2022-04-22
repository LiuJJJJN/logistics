package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
  private String id;
  private String code;
  private String name;
  private String fatherId;
  private String path;
  private String isMenu;
  private String roleId;

}
