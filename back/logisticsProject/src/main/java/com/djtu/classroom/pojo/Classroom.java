package com.djtu.classroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {

    private String id;

    private String roomNo;

    private String buildingId;

    private String electInf;

    private String isUse;

    private String isOpen;

    private String isClean;
}
