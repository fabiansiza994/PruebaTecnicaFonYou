package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.*;
import com.fmsp.fonyou.application.port.ExamStudentService;
import com.fmsp.fonyou.domain.Exam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetExamStudentUseCase {
    private final ExamStudentService examStudentService;

    public GetExamStudentUseCase(ExamStudentService examStudentService) {
        this.examStudentService = examStudentService;
    }

    public ExamDetailDto getExamStudent(Long studentId, Long examId){
        var exam = examStudentService.getExamStudent(studentId, examId);

        var questions = exam.stream().collect(Collectors.groupingBy(ExamProjectionDto::getQuestionId));

        var examDetailDto = new ExamDetailDto();

        List<ExamDetailDto.QuestionDetailDto> questionDtos = new ArrayList<>();
        questions.forEach((questionId, items) -> {
            items.forEach(item -> {
                examDetailDto.setExamId(item.getExamId());
                examDetailDto.setExamName(item.getExamName());

                var question = new ExamDetailDto.QuestionDetailDto();
                question.setId(questionId);
                question.setQuestionName(item.getQuestionName());

                question.setAnswerId(item.getAnswerId());
                question.setAnswerName(item.getAnswerName());
                question.setAnswerValue(false);
                questionDtos.add(question);
            });
        });

        examDetailDto.setQuestionDtoList(questionDtos);
        return examDetailDto;
    }
}
