package com.djtu.dorm.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormVo implements Serializable{
    private String id;
    private String doorNo;
    private long water;
    private long power;
    private long size;
    private String haveWc;
    private String remark;
    private String building;
    private String buildingId;

}
