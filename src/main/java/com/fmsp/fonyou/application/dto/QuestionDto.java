package com.fmsp.fonyou.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class QuestionDto {
    private Long id;
    private String questionName;
    private Long examId;
    private List<AnswerDto> answer;
}
