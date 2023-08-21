package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.port.ExamStudentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetExamByStudentAndStatusTrueUseCase {

    private final ExamStudentService examStudentService;

    public GetExamByStudentAndStatusTrueUseCase(ExamStudentService examStudentService) {
        this.examStudentService = examStudentService;
    }

    public Optional<ExamStudentReportDto> getExamByStudentAndStatusTrue(Long studentId, Long examId){
        return examStudentService.getExamByStudentAndStatusTrue(studentId, examId);
    }
}
