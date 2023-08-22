package com.fmsp.fonyou.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResultDto {
    private String name;
    private Double result;
    private List<QuestionDto> questionDtoList;
}
