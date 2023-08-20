package com.fmsp.fonyou.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QuestionDto {
    private Long id;
    private String questionName;
    private Long examId;
    private Double rate;
    private List<AnswerDto> answer;
}
