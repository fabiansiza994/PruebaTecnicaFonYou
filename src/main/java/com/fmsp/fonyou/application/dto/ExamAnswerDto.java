package com.fmsp.fonyou.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamAnswerDto {
    private String name;
    private String examJson;
    private Double result;
}
