package com.djtu.settings.dao;

import com.djtu.settings.pojo.Reply;

import java.util.List;

public interface ReplyDao {
    /**
     * 根据feedback_id获取reply实例
     * @param id feedback_id
     * @return reply列表
     */
    List<Reply> getReplyByFeedbackId(String feedbackId);
}
