package com.djtu.feedback.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackStudentVo {
    private String id;
    private String titleFeedback;
    private String time;
    private String replyTime;
    private String name;
}
