package com.djtu.feedback.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private String id;
    private String reply;
    private String tutorId;
    private String feedbackId;
    private String time;

}
