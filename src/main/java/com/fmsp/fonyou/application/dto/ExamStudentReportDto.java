package com.fmsp.fonyou.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class ExamStudentReportDto {
    private Long id;
    private Long studentId;
    private Long examId;
    private Long examJson;
    private Instant date;
    private Double result;
}
