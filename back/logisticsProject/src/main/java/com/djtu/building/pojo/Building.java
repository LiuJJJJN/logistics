package com.djtu.building.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building {
  private String id;
  private String name;
  private String floor;
  private long orderNo;
  private String type;
  private String rooms;


}
