package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DicValue implements Serializable {
  private String id;
  private String value;
  private Long orderNo;
  private String typeCode;

}
