package com.djtu.settings.dao;

public interface UserDao {

    /**
     * 根据学生 id 查找对应的用户 id
     * @param id 学生 id
     * @return 对应的用户 id
     */
    String getUserIdByStudentId(String id);

    /**
     * 根据导员 id 查找对应的用户 id
     * @param id 导员 id
     * @return 对应的用户 id
     */
    String getUserIdByTutorId(String id);

    /**
     * 根据管理员 id 查找对应的用户 id
     * @param id 管理员 id
     * @return 对应的用户 id
     */
    String getUserIdByAdminId(String id);
}
