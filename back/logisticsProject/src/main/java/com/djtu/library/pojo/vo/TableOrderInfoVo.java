package com.djtu.library.pojo.vo;

import com.djtu.library.pojo.LibOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableOrderInfoVo {
    private String id;
    private String floor;
    private String area;
    private long seat;
    private String name;
    private String status;
    private List<LibOrder> orderList;

}
