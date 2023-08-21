package com.fmsp.fonyou.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExamDto {
    private Long id;
    private String name;
    private List<QuestionDto> questionDtoList;

    @Setter
    @Getter
    public static class ExamStudentDto extends ExamDto{
        private Long studentId;
        private Long examId;
    }
}
