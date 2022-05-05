package com.djtu.dictionary.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DicValueVo implements Serializable {
    private String id;
    private String value;
    private Long orderNo;
    private String typeCode;
    private int pageNo;
    private int pageSize;

}
