package com.djtu.library.pojo;

import com.djtu.library.pojo.vo.LibraryVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibTable {
    private String id;
    private String floor;
    private String area;
    private long seat;
    private String name;
    private String status;
    private LibraryVo library;

}
