package com.djtu.settings.dao;

import com.djtu.settings.pojo.Reply;

import java.util.List;

public interface ReplyDao {
    /**
     * 根据feedback_id获取reply实例
     * @param  feedbackId
     * @return reply列表
     */
    List<Reply> getReplyByFeedbackId(String feedbackId);

    /**
     * 添加回复
     * @param reply1 回复实例
     * @return
     */
    Integer setReply(Reply reply1);
}
