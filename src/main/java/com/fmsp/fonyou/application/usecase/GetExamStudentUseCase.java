package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.ExamProjection;
import com.fmsp.fonyou.application.port.ExamStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetExamStudentUseCase {
    private final ExamStudentService examStudentService;

    public GetExamStudentUseCase(ExamStudentService examStudentService) {
        this.examStudentService = examStudentService;
    }

    public List<ExamProjection> getExamStudent(Long studentId, Long examId){
        var exam = examStudentService.getExamStudent(studentId, examId);

        return examStudentService.getExamStudent(studentId, examId);
    }
}
