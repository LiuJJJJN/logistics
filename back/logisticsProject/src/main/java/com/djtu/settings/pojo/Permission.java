package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private String id;
    private String code;
    private String name;
    private String roleId;
    private String path;
    private String isMenu;
}
