package com.fmsp.fonyou.application.dto;

public interface ExamProjection {
    String getExamName();
    Long getQuestionId();
    String getQuestionName();
    Long getAnswerId();
    String getAnswerName();
    Boolean getAnswerValue();
    Double getRate();
}
