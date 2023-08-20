package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.port.AssignExamService;
import com.fmsp.fonyou.config.DateConversionUtil;
import org.springframework.stereotype.Service;

@Service
public class AssignExamUseCase {

    private final AssignExamService assignExamUseCase;
    private final GetStudentByIdUseCase getStudentByIdUseCase;

    public AssignExamUseCase(AssignExamService assignExamUseCase, GetStudentByIdUseCase getStudentByIdUseCase) {
        this.assignExamUseCase = assignExamUseCase;
        this.getStudentByIdUseCase = getStudentByIdUseCase;
    }


    public ExamStudentReportDto AssignExam(ExamStudentReportDto examStudentReportDto){
        var student = getStudentByIdUseCase.getStudentById(examStudentReportDto.getStudentId());

        // cambiar hora de la prueba
        DateConversionUtil conversionService = new DateConversionUtil();
        var zonedDateTimeUpdated = conversionService.convertInstantToAnotherZone(examStudentReportDto.getDate(), student.getUtc());

        examStudentReportDto.setDate(zonedDateTimeUpdated.toInstant());

        return assignExamUseCase.assignExam(examStudentReportDto);
    }
}
