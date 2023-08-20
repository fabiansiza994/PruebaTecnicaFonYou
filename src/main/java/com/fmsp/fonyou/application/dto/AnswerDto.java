package com.fmsp.fonyou.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerDto {
    private Long id;
    private String name;
    private Boolean value;
    private Long questionId;
}
