package com.djtu.dorm.pojo;

import com.djtu.building.pojo.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dorm implements Serializable {
  private String id;
  private String doorNo;
  private long water;
  private long power;
  private long size;
  private String haveWc;
  private String remark;
  private Building building;

}
