package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DicValue {
  private String id;
  private String value;
  private Long orderNo;
  private String typeCode;

}
