package com.fmsp.fonyou.application.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.dto.ResultExamDto;
import com.fmsp.fonyou.application.port.AnswerService;
import com.fmsp.fonyou.application.port.ExamStudentService;
import com.fmsp.fonyou.config.CalculateRate;
import com.fmsp.fonyou.config.exception.ErrorOnUpdate;
import com.fmsp.fonyou.config.exception.ExamAlreadyAssigned;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrandingExamUseCase {

    private final ExamStudentService examStudentService;
    private final AnswerService answerService;
    private final GetStudentByIdUseCase getStudentByIdUseCase;
    private final GetExamByIdUseCase getExamByIdUseCase;
    private final GetExamByStudentAndStatusTrueUseCase getExamByStudentAndStatusTrueUseCase;
    private static final String EXAM_NOT_ASSIGNED = "El examen no esta asignado a este estudiante o ya fue presentado";
    public GrandingExamUseCase(ExamStudentService examStudentService, AnswerService answerService,
                               GetStudentByIdUseCase getStudentByIdUseCase, GetExamByIdUseCase getExamByIdUseCase,
                               GetExamByStudentAndStatusTrueUseCase getExamByStudentAndStatusTrueUseCase) {
        this.examStudentService = examStudentService;
        this.answerService = answerService;
        this.getStudentByIdUseCase = getStudentByIdUseCase;
        this.getExamByIdUseCase = getExamByIdUseCase;
        this.getExamByStudentAndStatusTrueUseCase = getExamByStudentAndStatusTrueUseCase;
    }


    @Transactional
    public ResultExamDto grandExam(ExamDto.ExamStudentDto examStudentDto){
        List<Double> rateList = new ArrayList<>();

        var examAssigned = getExamByStudentAndStatusTrueUseCase.getExamByStudentAndStatusTrue(examStudentDto.getStudentId(),
                examStudentDto.getExamId());

        if(examAssigned.isEmpty())
            throw new ExamAlreadyAssigned(EXAM_NOT_ASSIGNED);

        var rateDb = CalculateRate.calculateRate(examStudentDto.getQuestionDtoList().size());
        var studentData = getStudentByIdUseCase.getStudentById(examStudentDto.getStudentId());
        var exam = getExamByIdUseCase.getExamById(examStudentDto.getExamId());


        examStudentDto.getQuestionDtoList().forEach(questionDto -> {
             questionDto.getAnswer().forEach(answerDto -> {
                 var result = answerService.findCorrectAnswer(questionDto.getId(), answerDto.getId());
                 if(result != null){
                     if (result.getValue() == answerDto.getValue()){
                         if(Boolean.TRUE.equals(answerDto.getValue())){
                             rateList.add(rateDb);
                         }
                     }
                 }
             });
        });

        String json="";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            json = objectMapper.writeValueAsString(examStudentDto.getQuestionDtoList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        double total = 0.0;
        for (double rate : rateList) {
            total += rate;
        }

        var resultExam = new ResultExamDto();
        resultExam.setExamName(exam.getName());
        resultExam.setStudentName(studentData.getName());
        resultExam.setRating(total);
        int update = examStudentService.grandExam(resultExam.getRating(), examStudentDto.getExamId(),
                examStudentDto.getStudentId(), false, json);
        if(update == 0){
            throw new ErrorOnUpdate("Error on Update");
        }
        return resultExam;
    }
}
