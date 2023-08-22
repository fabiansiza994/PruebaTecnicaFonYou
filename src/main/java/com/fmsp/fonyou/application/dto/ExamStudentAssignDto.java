package com.fmsp.fonyou.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class ExamStudentAssignDto {
    private Long studentId;
    private Long examId;
    private Instant presentationDate;
    private Boolean status;

}
