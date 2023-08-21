package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.AnswerDto;
import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.dto.QuestionDto;
import com.fmsp.fonyou.application.port.ExamService;
import com.fmsp.fonyou.config.exception.AnswerNotFound;
import com.fmsp.fonyou.config.exception.QuestionNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateExamUseCase {

    private final ExamService examService;
    private final CreateQuestionUseCase createQuestionUseCase;
    private final CreateAnswerUseCase createAnswerUseCase;
    private static final String EXAM_NOT_HAVE_QUESTIONS = "Exam not contains Questions!!";
    private static final String QUESTION_NAME_IS_EMPTY = "Question Name is not Empty";
    private static final String EXAM_NOT_HAVE_ANSWERS = "Exam not contains Answers!!";

    public CreateExamUseCase(ExamService examService, CreateQuestionUseCase createQuestionUseCase, CreateAnswerUseCase createAnswerUseCase) {
        this.examService = examService;
        this.createQuestionUseCase = createQuestionUseCase;
        this.createAnswerUseCase = createAnswerUseCase;
    }

    @Transactional
    public ExamDto createExam(ExamDto examDto){
        var exam = examService.createExam(examDto);
        examDto.setId(exam.getId());

        if(examDto.getQuestionDtoList().isEmpty()){
            throw new QuestionNotFound(EXAM_NOT_HAVE_QUESTIONS);
        }

        var rate = calculateRate(examDto.getQuestionDtoList().size());

        proccessQuestion(examDto, exam.getId(), rate);
        return examDto;
    }

    public static double calculateRate(int questionCount) {
        return 100.0/questionCount;
    }

    private void proccessQuestion(ExamDto examDto, Long id, Double rate) {

        examDto.getQuestionDtoList().forEach(questionDto -> {
            var question = new QuestionDto();

            if(questionDto.getQuestionName().isEmpty())
                throw new QuestionNotFound(QUESTION_NAME_IS_EMPTY);

            question.setQuestionName(questionDto.getQuestionName());
            question.setExamId(id);
            question.setRate(rate);

            var questionDb = createQuestionUseCase.createQuestion(question);
            questionDto.setId(questionDb.getId());
            questionDto.setExamId(id);
            questionDto.setRate(rate);

            if(questionDto.getAnswer().isEmpty()){
                throw new AnswerNotFound(EXAM_NOT_HAVE_ANSWERS);
            }

            proccessAnswer(questionDto, questionDb.getId());
        });
    }

    private void proccessAnswer(QuestionDto questionDto, Long id) {
        questionDto.getAnswer().forEach(answerDto -> {
            var answer = new AnswerDto();

            if(answerDto.getName().isEmpty())
                throw new AnswerNotFound("Answer Name is not Empty");

            answer.setName(answerDto.getName());
            answer.setValue(answerDto.getValue());
            answer.setQuestionId(id);

            var answerDb = createAnswerUseCase.createAnswer(answer);
            answerDto.setId(answerDb.getId());
            answerDto.setQuestionId(id);
        });
    }
}
