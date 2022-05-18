package com.djtu.library.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTableVo {
    private String id;
    private String libraryId;
    private String libraryName;
    private String status;
    private String seat;
    private String floor;
    private String area;
    private String name;

}
