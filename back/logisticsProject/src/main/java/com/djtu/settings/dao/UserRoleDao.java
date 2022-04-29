package com.djtu.settings.dao;

import com.djtu.settings.pojo.User;
import com.djtu.settings.pojo.UserRole;

import java.util.List;

public interface UserRoleDao {
    /**
     * 学生注册插入
     * @param userRole
     * @return
     */
    Integer setUserRole(UserRole userRole);

    /**
     * 根据userId删除tbl_user_role中的记录
     * @param list
     * @return
     */
    Integer delByUserId(List<User> list);
}
