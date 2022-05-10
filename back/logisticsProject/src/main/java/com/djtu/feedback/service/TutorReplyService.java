package com.djtu.feedback.service;

import com.djtu.exception.ReplyException;
import com.djtu.feedback.pojo.Feedback;
import com.djtu.feedback.pojo.Reply;
import com.djtu.settings.pojo.User;

import java.util.List;
import java.util.Map;

public interface TutorReplyService {

    /**
     * 获取未反馈的数量
     *
     * @param user id
     * @return 数量
     */
    Map<String, Object> getUnNum(User user);

    /**
     * 获取导员为回复的反馈列表
     *
     * @param user id
     * @return 未反馈的列表
     */
    Map<String, Object> getUnReplyList(User user);

    /**
     * 根据id获取feedback实例
     *
     * @param id id
     * @return 反馈实例
     */
    Feedback getFeedbackInfById(String id);

    /**
     * 导员回复反馈
     *
     * @param reply 回复实例 id是userid，tutorid是反馈id，reply是回复内容
     */
    void tutorReply(Reply reply) throws ReplyException;

    /**
     * 据stuid获取feedback，条件导员回复过以及没有被删除的记录数
     *
     * @param user 用户实例
     * @return map集合
     */
    Map<String, Object> getReplyList(User user);

    /**
     * 根据id获取历史反馈内容
     *
     * @param feedback
     * @return
     */
    Feedback getHistoryInfById(Feedback feedback);

    /**
     * 获取答复的信息
     *
     * @param feedback feedback实例
     * @return 答复实例
     */
    List<Reply> getReplyInfById(Feedback feedback);

    /**
     * * 再次回复学生反馈
     * @param reply 答复实例
     * @param reply
     * @throws ReplyException
     */
    void replyAgain(Reply reply) throws ReplyException;
}
