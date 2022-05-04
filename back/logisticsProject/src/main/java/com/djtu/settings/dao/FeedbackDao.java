package com.djtu.settings.dao;

import com.djtu.settings.pojo.Feedback;
import com.djtu.settings.pojo.Reply;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.vo.FeedbackStudentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackDao {
    /**
     * 添加反馈
     * @param feedback 反馈实例
     * @return 影响条数
     */
    Integer studentAddFeedback(Feedback feedback);

    /**
     * 根据stu_id获取反馈列表
     * @param stuId
     * @return
     */
    List<Feedback> getFeedbackByStudent(@Param("stuId") String stuId,@Param("pageNo") int pageNo,@Param("pageSize") int pageSize);

    /**
     * 获取stu_id为stuId的记录个数
     * @param stuId 学生id
     * @return 记录个数
     */
    Integer getListNum(String stuId);

    /**
     * 未答复的历史记录不得删除
     * @param data
     * @return
     */
    Integer delLimit(List<String> data);
    /**
     * 删除历史记录-实质是把标记改为1
     * @param list id列表
     * @return 更新的条数
     */
    Integer updateFeedbackById(List<String> list);

    /**
     * 根据id获取反馈
     * @param id 反馈主键
     * @return 反馈实例
     */
    Feedback getDetailedInfById(String id);

    /**
     * 更新反馈内容
     * @param feedback
     * @return
     */
    Integer updateDetail(@Param("feedback") String feedback,@Param("id")String id);

    /**
     * 根据学生id获取反馈表里的id、titleFeed和time以及student表里的名字,条件是未反馈的
     * @param pageNo 第几页
     * @param pageSize 显示几条
     * @param list 学生列表
     * @return
     */
    List<FeedbackStudentVo> getFeedbackAndStuListByStuId(@Param("pageNo")int pageNo, @Param("pageSize")int pageSize, @Param("list") List<Student> list);

    /**
     * 根据学生id获取反馈表理的未反馈的记录数
     * @param list 学生列表
     * @return 记录数
     */
    Integer getNum(List<Student> list);

    /**
     * 根据id获取feedback实例
     * @param id id
     * @return 反馈实例
     */
    Feedback getFeedbackById(String id);

    /**
     * 将学生反馈状态设置为3
     * @param id 反馈id
     * @return 记录数
     */
    Integer setStatus(String id);

    /**
     * 根据stuid获取feedback，条件导员回复过以及没有被删除的
     * @param pageNo,pageSize,list 页码 数量 学生列表
     * @return FeedbackStudentVo实体
     */
    List<FeedbackStudentVo> getFeedbackByStuIdWhereStatusDelFlag(@Param("pageNo") int pageNo,@Param("pageSize") int pageSize, @Param("list") List<Student> list);

    /**
     * 根据stuid获取feedback，条件导员回复过以及没有被删除的记录数
     * @param stuList 学生列表
     * @return
     */
    Integer getReplyHistoryNum(List<Student> stuList);

    /**
     * 根据id获取历史反馈内容
     * @param id 反馈id
     * @return 反馈实例
     */
    Feedback getHistoryInfById(String id);

    /**
     * 获取答复的信息根据id
     * @param id 反馈id
     * @return 答复实例
     */
    List<Reply> getReplyInfById(String id);

}
