package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.port.ExamService;
import org.springframework.stereotype.Service;

@Service
public class GetExamByIdUseCase {
    private final ExamService examService;

    public GetExamByIdUseCase(ExamService examService) {
        this.examService = examService;
    }

    public ExamDto getExamById(Long examId){
        return examService.getExamById(examId);
    }
}
