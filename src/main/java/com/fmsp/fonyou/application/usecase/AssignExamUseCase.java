package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.port.AssignExamService;
import com.fmsp.fonyou.config.DateConversionUtil;
import com.fmsp.fonyou.config.exception.ExamAlreadyAssigned;
import org.springframework.stereotype.Service;

@Service
public class AssignExamUseCase {

    private final AssignExamService assignExamUseCase;
    private final GetStudentByIdUseCase getStudentByIdUseCase;
    private final GetExamByStudentAndStatusTrueUseCase getExamByStudentAndStatusTrueUseCase;
    private static final String EXAM_ALREADY_ASSIGNED = "El examen ya se encuentra asginado";

    public AssignExamUseCase(AssignExamService assignExamUseCase, GetStudentByIdUseCase getStudentByIdUseCase, GetExamByStudentAndStatusTrueUseCase getExamByStudentAndStatusTrueUseCase) {
        this.assignExamUseCase = assignExamUseCase;
        this.getStudentByIdUseCase = getStudentByIdUseCase;
        this.getExamByStudentAndStatusTrueUseCase = getExamByStudentAndStatusTrueUseCase;
    }


    public ExamStudentReportDto AssignExam(ExamStudentReportDto examStudentReportDto){
        var student = getStudentByIdUseCase.getStudentById(examStudentReportDto.getStudentId());

        var exam = getExamByStudentAndStatusTrueUseCase.getExamByStudentAndStatusTrue(examStudentReportDto.getStudentId(),
                examStudentReportDto.getExamId());

        if(exam.isPresent())
            throw new ExamAlreadyAssigned(EXAM_ALREADY_ASSIGNED);

        DateConversionUtil conversionService = new DateConversionUtil();
        var zonedDateTimeUpdated = conversionService.convertInstantToUtcOffset(examStudentReportDto.getDate(), student.getUtc());

        examStudentReportDto.setDate(zonedDateTimeUpdated);
        examStudentReportDto.setStatus(true);
        return assignExamUseCase.assignExam(examStudentReportDto);
    }
}
