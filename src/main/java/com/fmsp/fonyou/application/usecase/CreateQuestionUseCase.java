package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.QuestionDto;
import com.fmsp.fonyou.application.port.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class CreateQuestionUseCase {

    private final QuestionService questionService;

    public CreateQuestionUseCase(QuestionService questionService) {
        this.questionService = questionService;
    }

    public QuestionDto createQuestion(QuestionDto questionDto){
        return questionService.createQuestion(questionDto);
    }
}
