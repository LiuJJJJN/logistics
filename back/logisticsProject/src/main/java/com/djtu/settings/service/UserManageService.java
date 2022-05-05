package com.djtu.settings.service;

import com.djtu.exception.UserManagerException;
import com.djtu.dictionary.pojo.DicValue;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.vo.StudentSearchVo;
import com.djtu.settings.pojo.vo.TutorVo;

import java.util.List;

public interface UserManageService {
    /**
     * 获取导员信息
     * @param tutorVo 导员实例
     * @return 导员信息列表
     */
    List<Tutor> getTutorList(TutorVo tutorVo) throws UserManagerException;

    /**
     * 导员记录总个数
     * @return 总个数
     */
    Integer gitTutorNum();

    /**
     * 学院列表
     * @return 学院列表
     */
    List<DicValue> getCollegeList();

    /**
     *重置导员密码6个0
     * @param data id列表
     */
    void resetTutorPwd(List<String> data) throws UserManagerException;

    /**
     * 批量删除导员
     * @param data id列表
     */
    void delTutorList(List<String> data) throws UserManagerException;

    /**
     * 添加或修改备注
     * @param tutor 导员实例
     */
    void addOrUpTutorRemark(Tutor tutor) throws UserManagerException;

    /**
     * 模糊查询、分页查询 学生列表
     * @param studentSearchVo 模糊查询信息
     * @param pageNo 页码
     * @param pageSize 数据量
     * @return 学生列表
     */
    List<Student> getStudentList(StudentSearchVo studentSearchVo, Integer pageNo, Integer pageSize);

    /**
     * 批量删除学生
     * @param stuList 学生列表
     */
    void delStudentList(List<String> stuList) throws UserManagerException;

    /**
     * 修改学生密码
     * @param id 学生id
     */
    void resetStudentPwd(String id) throws UserManagerException;

    /**
     * 修改学生的导员外键为导员id
     * @param stuId 学生id
     * @param tutorId 导员id
     */
    void editStudentTutorIdById(String stuId, String tutorId) throws UserManagerException;

    /**
     * 模糊查询、分页查询 学生列表
     * @param tutorId 导员id
     * @param studentSearchVo 模糊查询信息
     * @param pageNo 页码
     * @param pageSize 数据量
     * @return 学生列表
     */
    List<Student> getStudentList(String tutorId, StudentSearchVo studentSearchVo, Integer pageNo, Integer pageSize);

    /**
     * 导员抛弃学生，删除学生的导员外键
     * @param stuId 学生id
     */
    void delStudentTutorIdByStudentId(String stuId) throws UserManagerException;
}
