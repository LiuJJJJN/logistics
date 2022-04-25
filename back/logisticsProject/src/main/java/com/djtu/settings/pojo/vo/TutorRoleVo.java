package com.djtu.settings.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorRoleVo {
    String id;
    String username;
    String name;
    String sex;
    String college;
    List<String> perms;
    String remark;
}
