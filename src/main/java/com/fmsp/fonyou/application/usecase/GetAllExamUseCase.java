package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.port.ExamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllExamUseCase {

    private final ExamService examService;

    public GetAllExamUseCase(ExamService examService) {
        this.examService = examService;
    }

    public List<ExamDto> getExamList(){
        return examService.getExamList();
    }
}
