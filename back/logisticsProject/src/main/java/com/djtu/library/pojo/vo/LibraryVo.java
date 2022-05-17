package com.djtu.library.pojo.vo;

import com.djtu.building.pojo.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryVo {

    private String id;
    private String name;
    private String openTime;
    private String closeTime;
    private String isOpen;
    private Building building;

}
