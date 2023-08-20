package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.port.ExamStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllExamByStudentIdUseCase {
    private final ExamStudentService examStudentService;

    public GetAllExamByStudentIdUseCase(ExamStudentService examStudentService) {
        this.examStudentService = examStudentService;
    }

    public List<ExamStudentReportDto> getExamByStudentIdList(Long studentId){
        return examStudentService.getExamByStudentIdList(studentId);
    }
}
