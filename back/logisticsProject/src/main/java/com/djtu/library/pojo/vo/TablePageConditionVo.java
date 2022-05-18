package com.djtu.library.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TablePageConditionVo {
    private String libraryId;
    private String floor;
    private String area;
    private String status;
    private Integer pageNo;
    private Integer pageSize;

}
