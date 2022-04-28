package com.djtu.settings.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DicTypeVo implements Serializable {
    private String id;
    private String code;
    private String name;
    private String description;
    private int pageNo;
    private int pageSize;
}
