package com.djtu.feedback.service;

import com.djtu.exception.FeedbackException;
import com.djtu.feedback.pojo.Feedback;
import com.djtu.settings.pojo.User;

import java.util.List;
import java.util.Map;

public interface StudentFeedbackService {

    /**
     * 学生添加反馈
     *
     * @param feedback 反馈实例
     */
    void studentAddFeedback(Feedback feedback) throws FeedbackException;

    /**
     * 获取学生反馈列表
     *
     * @param user 用户实例
     * @return 学生反馈列表
     */
    List<Feedback> studentFeedbackList(User user) throws FeedbackException;

    /**
     * 获取总记录数
     *
     * @return 记录数
     */
    Integer getListNum(String id) throws FeedbackException;

    /**
     * 删除历史记录-实质是把标记改为1
     *
     * @param data 要删除的记录id
     */
    void delStudentFeedbackList(List<String> data) throws FeedbackException;

    /**
     * 根据id获取反馈
     *
     * @param id 反馈主键id
     * @return 反馈实例
     */
    Map<String, Object> getDetailedInf(String id) throws FeedbackException;

    /**
     * 更新反馈内容
     *
     * @param f 修改后的反馈内容
     */
    void updateDetail(String f, String id) throws FeedbackException;
}
