package com.djtu.building.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingValueVo {
    private String id;
    private String value;
    private String floor;
    private long orderNo;
    private String type;
    private String rooms;

}
