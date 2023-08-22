package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.port.AssignExamService;
import com.fmsp.fonyou.config.DateConversionUtil;
import com.fmsp.fonyou.config.exception.ExamAlreadyAssigned;
import com.fmsp.fonyou.config.exception.ExamNotFound;
import org.springframework.stereotype.Service;

@Service
public class AssignExamUseCase {

    private final AssignExamService assignExamUseCase;
    private final GetStudentByIdUseCase getStudentByIdUseCase;
    private final GetExamByStudentAndStatusTrueUseCase getExamByStudentAndStatusTrueUseCase;
    private final GetExamByIdUseCase getExamByIdUseCase;
    private static final String EXAM_ALREADY_ASSIGNED = "El examen ya se encuentra asginado";
    private static final String EXAM_NOT_FOUND = "Examen no econtrado";

    public AssignExamUseCase(AssignExamService assignExamUseCase, GetStudentByIdUseCase getStudentByIdUseCase,
                             GetExamByStudentAndStatusTrueUseCase getExamByStudentAndStatusTrueUseCase, GetExamByIdUseCase getExamByIdUseCase) {
        this.assignExamUseCase = assignExamUseCase;
        this.getStudentByIdUseCase = getStudentByIdUseCase;
        this.getExamByStudentAndStatusTrueUseCase = getExamByStudentAndStatusTrueUseCase;
        this.getExamByIdUseCase = getExamByIdUseCase;
    }


    public ExamStudentReportDto AssignExam(ExamStudentReportDto examStudentReportDto){
        var student = getStudentByIdUseCase.getStudentById(examStudentReportDto.getStudentId());
        var examExist = getExamByIdUseCase.getExamById(examStudentReportDto.getExamId());

        if(examExist == null){
            throw new ExamNotFound(EXAM_NOT_FOUND);
        }

        var exam = getExamByStudentAndStatusTrueUseCase.getExamByStudentAndStatusTrue(examStudentReportDto.getStudentId(),
                examStudentReportDto.getExamId());

        if(exam.isPresent())
            throw new ExamAlreadyAssigned(EXAM_ALREADY_ASSIGNED);

        DateConversionUtil conversionService = new DateConversionUtil();
        var zonedDateTimeUpdated = conversionService.convertInstantToUtcOffset(examStudentReportDto.getPresentationDate(),
                student.getUtc());

        examStudentReportDto.setPresentationDate(zonedDateTimeUpdated);
        examStudentReportDto.setStatus(true);
        examStudentReportDto.setResult(0.0);
        return assignExamUseCase.assignExam(examStudentReportDto);
    }
}
