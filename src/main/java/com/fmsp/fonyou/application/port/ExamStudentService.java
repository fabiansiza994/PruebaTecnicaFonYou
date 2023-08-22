package com.fmsp.fonyou.application.port;

import com.fmsp.fonyou.application.dto.ExamAnswerDto;
import com.fmsp.fonyou.application.dto.ExamProjectionDto;
import com.fmsp.fonyou.application.dto.ExamStudentAssignDto;
import com.fmsp.fonyou.application.dto.ExamStudentReportDto;

import java.util.List;
import java.util.Optional;

public interface ExamStudentService {
    List<ExamStudentAssignDto> getExamByStudentIdList(Long studentId);
    Optional<ExamStudentReportDto> getExamByStudentAndStatusTrue(Long studentId, Long examId);

    List<ExamProjectionDto> getExamStudent(Long studentId, Long examId);

    ExamAnswerDto getExamStudentAnswer(Long studentId, Long examId);

    int grandExam(Double rate, Long examId, Long studentId, Boolean status, String json);
}
