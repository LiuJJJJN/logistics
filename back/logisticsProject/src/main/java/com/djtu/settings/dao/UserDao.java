package com.djtu.settings.dao;

public interface UserDao {

    String getUserIdByStudentId(String id);

    String getUserIdByTutorId(String id);

    String getUserIdByAdminId(String id);
}
