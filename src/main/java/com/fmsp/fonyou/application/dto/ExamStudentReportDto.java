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
    private String examJson;
    private Instant presentationDate;
    private Double result;
    private Boolean status;
}
