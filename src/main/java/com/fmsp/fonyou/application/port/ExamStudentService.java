package com.fmsp.fonyou.application.port;

import com.fmsp.fonyou.application.dto.ExamProjection;
import com.fmsp.fonyou.application.dto.ExamStudentReportDto;

import java.util.List;
import java.util.Optional;

public interface ExamStudentService {
    List<ExamStudentReportDto> getExamByStudentIdList(Long studentId);
    Optional<ExamStudentReportDto> getExamByStudentAndStatusTrue(Long studentId, Long examId);

    List<ExamProjection> getExamStudent(Long studentId, Long examId);
}
