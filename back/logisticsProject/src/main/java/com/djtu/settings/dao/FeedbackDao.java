package com.djtu.settings.dao;

import com.djtu.settings.pojo.Feedback;

public interface FeedbackDao {
    /**
     * 添加反馈
     * @param feedback 反馈实例
     * @return 影响条数
     */
    Integer studentAddFeedback(Feedback feedback);
}
