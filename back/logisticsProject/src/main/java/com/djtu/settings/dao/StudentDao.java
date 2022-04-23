package com.djtu.settings.dao;

import com.djtu.settings.pojo.Student;
import com.djtu.settings.vo.UserVo;

public interface StudentDao {
    /**
     *根据学生的用户名查询
     * @param username
     * @return
     */
    Student getStudentByUsername(String username);

    /**
     * 根据学生的学号查询
     * @param sno
     * @return
     */
    Student getStudentBySno(String sno);

    /**
     * 注册学生
     * @param student
     * @return
     */
    Integer setStudent(Student student);
}
