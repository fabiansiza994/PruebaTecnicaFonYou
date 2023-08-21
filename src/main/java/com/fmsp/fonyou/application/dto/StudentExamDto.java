package com.fmsp.fonyou.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudentExamDto {

    private List<QuestionDto> questionDtoList;
}
