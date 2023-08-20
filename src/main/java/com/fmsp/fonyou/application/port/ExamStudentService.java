package com.fmsp.fonyou.application.port;

import com.fmsp.fonyou.application.dto.ExamStudentReportDto;

import java.util.List;

public interface ExamStudentService {
    List<ExamStudentReportDto> getExamByStudentIdList(Long studentId);
}
