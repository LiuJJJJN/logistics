package com.djtu.classroom.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingClassroomVo {

    private String buildingId;

    private String buildingName;

    private String buildingFloor;

    private String buildingOrderNo;

    private String buildingType;

    private String buildingRooms;

    private String classroomId;

    private String classroomRoomNo;

    private String classroomBuildingId;

    private String classroomElectInf;

    private String classroomIsUse;

    private String classroomIsOpen;

    private String classroomIsClean;




}
