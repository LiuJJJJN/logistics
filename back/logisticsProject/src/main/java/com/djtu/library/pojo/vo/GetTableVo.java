package com.djtu.library.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTableVo {
    private String library;
    private String floor;
    private String date;
    private String area;
    private String stuId;

}
