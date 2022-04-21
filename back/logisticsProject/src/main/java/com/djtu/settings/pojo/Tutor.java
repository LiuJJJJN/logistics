package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
  private String id;
  private String address;
  private String college;
  private String dormId;
  private String remark;

}
