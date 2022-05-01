package com.djtu.settings.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private String id;
    private String feedback;
    private String titleFeedback;
    private String stuId;
    private String time;
    private String status;

}
