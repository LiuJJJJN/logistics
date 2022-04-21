package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DicValue {
  private String id;
  private String value;
  private String text;
  private Long orderNo;
  private String typeCode;

}
