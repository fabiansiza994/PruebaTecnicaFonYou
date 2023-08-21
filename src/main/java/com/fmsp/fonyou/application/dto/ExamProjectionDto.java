package com.fmsp.fonyou.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExamProjectionDto {
    private String examName;
    private Long questionId;
    private String questionName;
    private Long answerId;
    private String answerName;
    private Boolean answerValue;
    private Double rate;
}
