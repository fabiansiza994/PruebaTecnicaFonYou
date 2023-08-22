package com.fmsp.fonyou.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExamDetailDto {
    private String examName;
    private Long examId;
    private List<QuestionDetailDto> questionDtoList;


    @Setter
    @Getter
    public static class QuestionDetailDto {
        private Long id;
        private String questionName;
        private Long answerId;
        private String answerName;
        private Boolean answerValue = false;
    }
}
