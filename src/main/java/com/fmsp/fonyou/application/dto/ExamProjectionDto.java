package com.fmsp.fonyou.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamProjectionDto {
    private Long examId;
    private String examName;
    private Long questionId;
    private String questionName;
    private Long answerId;
    private String answerName;
    private Boolean answerValue;
    private Double rate;
}
