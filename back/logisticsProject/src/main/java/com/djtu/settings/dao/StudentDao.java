package com.djtu.settings.dao;

import com.djtu.settings.pojo.Student;
import com.djtu.settings.vo.UserVo;

public interface StudentDao {

    /**
     * 根据用户名查询学生信息
     * @param username 学生用户名
     * @return 学生实例
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
