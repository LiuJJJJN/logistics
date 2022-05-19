package com.djtu.library.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibOrder {
    private String id;
    private String stuId;
    private String startTime;
    private String endTime;
    private String createTime;
    private String tableId;


}
