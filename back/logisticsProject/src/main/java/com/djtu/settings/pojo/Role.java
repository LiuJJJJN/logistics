package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 角色表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    private String id;
    private String name;
}
