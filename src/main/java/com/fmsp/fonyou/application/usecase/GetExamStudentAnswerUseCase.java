package com.fmsp.fonyou.application.usecase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmsp.fonyou.application.dto.AnswerResultDto;
import com.fmsp.fonyou.application.dto.QuestionDto;
import com.fmsp.fonyou.application.port.ExamStudentService;
import com.fmsp.fonyou.config.exception.AnswerNotFound;
import com.fmsp.fonyou.config.exception.ExamNotFound;
import com.fmsp.fonyou.config.exception.StudentNotFound;
import com.fmsp.fonyou.domain.Answer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetExamStudentAnswerUseCase {

    private final ExamStudentService examStudentService;
    private final GetExamByIdUseCase getExamByIdUseCase;
    private final GetStudentByIdUseCase getStudentByIdUseCase;
    private static final String EXAM_NOT_FOUND = "Examen no econtrado";
    private static final String STUDENT_NOT_FOUND = "Estudiante no encontrado en el registro";
    private static final String EXAM_IS_PENDING = "El examen no ha sido resuelto";

    public GetExamStudentAnswerUseCase(ExamStudentService examStudentService, GetExamByIdUseCase getExamByIdUseCase, GetStudentByIdUseCase getStudentByIdUseCase) {
        this.examStudentService = examStudentService;
        this.getExamByIdUseCase = getExamByIdUseCase;
        this.getStudentByIdUseCase = getStudentByIdUseCase;
    }

    public AnswerResultDto getExamStudentAnswer(Long studentId, Long examId){
        var examExist = getExamByIdUseCase.getExamById(examId);
        var student = getStudentByIdUseCase.getStudentById(studentId);

        if(examExist == null){
            throw new ExamNotFound(EXAM_NOT_FOUND);
        }

        if(student == null){
            throw new StudentNotFound(STUDENT_NOT_FOUND);
        }

        var answers = examStudentService.getExamStudentAnswer(studentId, examId);

        if(answers == null){
            throw new AnswerNotFound(EXAM_IS_PENDING);
        }

        List<QuestionDto> questionsList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            questionsList = objectMapper.readValue(answers.getExamJson(), new TypeReference<List<QuestionDto>>() {});

        } catch (Exception e) {
            e.printStackTrace();
        }

        var answerResult = new AnswerResultDto();
        answerResult.setName(answers.getName());
        answerResult.setResult(answers.getResult());
        answerResult.setQuestionDtoList(questionsList);
        return answerResult;
    }
}
