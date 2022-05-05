package com.djtu.feedback.dao;

import com.djtu.feedback.pojo.Feedback;
import com.djtu.feedback.pojo.Reply;
import com.djtu.settings.pojo.Student;
import com.djtu.feedback.pojo.vo.FeedbackStudentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackDao {
    /**
     * 添加反馈
     *
     * @param feedback 反馈实例
     * @return 影响条数
     */
    Integer studentAddFeedback(Feedback feedback);

    /**
     * 根据stu_id获取反馈列表
     *
     * @param stuId 学生id
     * @return 反馈列表
     */
    List<Feedback> getFeedbackByStudent(@Param("stuId") String stuId,
                                        @Param("pageNo") int pageNo,
                                        @Param("pageSize") int pageSize);

    /**
     * 获取stu_id为stuId的记录个数
     *
     * @param stuId 学生id
     * @return 记录个数
     */
    Integer getListNum(String stuId);

    /**
     * 未答复的历史记录不得删除
     *
     * @param data 要删除的反馈id列表
     * @return 影响条数
     */
    Integer delLimit(List<String> data);

    /**
     * 删除历史记录-实质是把标记改为1
     *
     * @param list id列表
     * @return 更新的条数
     */
    Integer updateFeedbackById(List<String> list);

    /**
     * 根据id获取反馈
     *
     * @param id 反馈主键
     * @return 反馈实例
     */
    Feedback getDetailedInfById(String id);

    /**
     * 更新反馈内容
     *
     * @param feedback 反馈信息
     * @return 影响条数
     */
    Integer updateDetail(@Param("feedback") String feedback, @Param("id") String id);

    /**
     * 根据学生id获取反馈表里的id、titleFeed和time以及student表里的名字,条件是未反馈的
     *
     * @param pageNo   第几页
     * @param pageSize 显示几条
     * @param list     学生列表
     * @return 学生反馈列表及学生列表
     */
    List<FeedbackStudentVo> getFeedbackAndStuListByStuId(@Param("pageNo") int pageNo,
                                                         @Param("pageSize") int pageSize,
                                                         @Param("list") List<Student> list);

    /**
     * 根据学生id获取反馈表理的未反馈的记录数
     *
     * @param list 学生列表
     * @return 记录数
     */
    Integer getNum(List<Student> list);

    /**
     * 根据id获取feedback实例
     *
     * @param id id
     * @return 反馈实例
     */
    Feedback getFeedbackById(String id);

    /**
     * 将学生反馈状态设置为3
     *
     * @param id 反馈id
     * @return 记录数
     */
    Integer setStatus(String id);

    /**
     * 根据stuid获取feedback，条件导员回复过以及没有被删除的
     *
     * @param pageNo,pageSize,list 页码 数量 学生列表
     * @return FeedbackStudentVo实体
     */
    List<FeedbackStudentVo> getFeedbackByStuIdWhereStatusDelFlag(@Param("pageNo") int pageNo,
                                                                 @Param("pageSize") int pageSize,
                                                                 @Param("list") List<Student> list);

    /**
     * 根据stuId获取feedback，条件导员回复过以及没有被删除的记录数
     *
     * @param stuList 学生列表
     * @return 导员回复过以及没有被删除的记录数
     */
    Integer getReplyHistoryNum(List<Student> stuList);

    /**
     * 根据id获取历史反馈内容
     *
     * @param id 反馈id
     * @return 反馈实例
     */
    Feedback getHistoryInfById(String id);

    /**
     * 获取答复的信息根据id
     *
     * @param id 反馈id
     * @return 答复实例
     */
    List<Reply> getReplyInfById(String id);

    /**
     * 根据feedback_id获取reply实例列表
     *
     * @param feedbackId 反馈id
     * @return reply列表
     */
    List<Reply> getReplyByFeedbackId(String feedbackId);

    /**
     * 添加回复
     *
     * @param reply1 回复实例
     * @return 影响条数
     */
    Integer setReply(Reply reply1);

}
