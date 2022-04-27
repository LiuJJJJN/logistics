package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dorm {
  private String id;
  private String doorNo;
  private long water;
  private long power;
  private long size;
  private String haveWc;
  private String remark;

}
