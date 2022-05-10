package com.djtu.building.pojo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building implements Serializable {
  private String id;
  private String name;
  private String floor;
  private long orderNo;
  private String type;
  private String rooms;

}
