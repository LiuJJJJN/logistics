package com.djtu.settings.dao;

import com.djtu.permission.pojo.vo.StudentDormVo;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.vo.StudentSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param student 学生信息
     * @return 插入成功数量
     */
    Integer setStudent(Student student);

    /**
     * 获取学生总数
     * @return 学生数量
     */
    Integer getStudentTotal();

    /**
     * 根据学生id修改学生信息
     * @param student 学生实例
     * @return 影响条数
     */
    int editStudent(Student student);

    /**
     * 根据学生id查询学生信息
     * @param id 学生id
     * @return 学生实例
     */
    Student getStudentById(String id);

    /**
     * 查询库中用户名的数量
     * @param username 用户名
     * @return 有多少相同用户名
     */
    int getUsernameCountByUsername(String username);

    /**
     * 查询库中相同学号的数量
     * @param sno 学号
     * @return 有多少相同学号
     */
    int getSnoCountBySno(String sno);

    /**
     * 根据学生id获取盐
     * @param id 学生id
     * @return 盐
     */
    String getStudentSaltById(String id);

    /**
     * 根据模糊查询分页条件查询学生数据
     * @param studentSearchVo 模糊查询
     * @param pageNo 页码
     * @param pageSize 数据量
     * @return 学生列表
     */
    List<Student> getStudentListByPageCondition(@Param("vo") StudentSearchVo studentSearchVo,
                                                @Param("pageNo") Integer pageNo,
                                                @Param("pageSize") Integer pageSize);

    /**
     * 根据学生id修改备注
     * @param id id
     * @param remark 备注
     * @return 影响条数
     */
    int editStudentRemarkById(@Param("id") String id, @Param("remark") String remark);

    /**
     * 批量删除学生
     * @param stuList 学生id列表
     * @return 删除条数
     */
    int deleteStudentByStuList(List<String> stuList);

    /**
     * 根据id修改学生密码和盐
     * @param id 学生id
     * @param password 加密后的密码
     * @param salt 盐
     * @return 影响条数
     */
    int editStudentPwdById(@Param("id") String id, @Param("pwd") String password, @Param("salt") String salt);

    /**
     * 为指定username的学生设置头像路径
     * @param username 用户名
     * @param avatarPath 头像路径
     * @return 影响条数
     */
    int setStudentAvatarPathByUsername(@Param("username") String username, @Param("avatarPath") String avatarPath);

    /**
     * 在学生表中查询stu_id是tutorId的学生记录
     * @param tutorId 学生id
     * @return 学生列表
     */
    List<Student> getStudentListByTutorId(String tutorId);


    /**
     * 根据学生id修改对应导员外键id
     * @param stuId 学生id
     * @param tutorId 导员id
     * @return 影响条数
     */
    int editStudentTutorIdById(@Param("stuId") String stuId, @Param("tutorId") String tutorId);

    /**
     * 分页 模糊查询 导员所属的学生
     * @param tutorId 导员id
     * @param studentSearchVo 搜索内容
     * @param pageNo 页码
     * @param pageSize 数据量
     * @return 学生列表
     */
    List<Student> getStudentListByPageConditionAndTutorId(@Param("tutorId") String tutorId,
                                                          @Param("vo") StudentSearchVo studentSearchVo,
                                                          @Param("pageNo") Integer pageNo,
                                                          @Param("pageSize") Integer pageSize);

    /**
     * 根据导员id获取对应的学生总数
     * @param tutorId 导员id
     * @param studentSearchVo 模糊查询条件
     * @return 学生总数
     */
    Integer getStudentByTutorTotal(@Param("tutorId") String tutorId, @Param("vo") StudentSearchVo studentSearchVo);

    /**
     * 设置学生的导员外键为空
     * @param stuId 学生id
     * @return 影响条数
     */
    int setStudentTutorId2Null(String stuId);

    /**
     * 获取学生信息包括寝室
     * @param tutorId
     * @return 学生和寝室信息实例
     */
    List<StudentDormVo> getStudentAndDormInfByTutorId(String tutorId);

    /**
     * 修改学生寝室
     * @param id 学生id
     * @param dormId 寝室id
     * @return 影响条数
     */
    int editStudentDormById(@Param("id") String id, @Param("dormId") String dormId);

    /**
     * 根据寝室id查询寝室现有人数
     *
     * @param dormId 寝室id
     * @return 现有人数
     */
    int countDormByDormId(String dormId);

    /**
     * 完成换寝：设置寝室为换寝目标寝室
     *
     * @param stuId 学生id
     * @return 影响条数
     */
    int setNewDorm(String stuId);

    /**
     * 根据学生id获取导员id(是否有导员)
     *
     * @param stuId 学生id
     * @return 导员id
     */
    String getTutorIdByStuId(String stuId);
}
