package com.djtu.settings.service;

import com.djtu.exception.FeedbackException;
import com.djtu.settings.pojo.Feedback;

public interface UserFeedbackService {

    /**
     * 学生添加反馈
     * @param feedback 反馈实例
     */
    void studentAddFeedback(Feedback feedback) throws FeedbackException;
}
