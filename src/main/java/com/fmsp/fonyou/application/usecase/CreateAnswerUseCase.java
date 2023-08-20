package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.AnswerDto;
import com.fmsp.fonyou.application.port.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class CreateAnswerUseCase {

    private final AnswerService answerService;

    public CreateAnswerUseCase(AnswerService answerService) {
        this.answerService = answerService;
    }

    public AnswerDto createAnswer(AnswerDto answerDto){
        return answerService.createAnswer(answerDto);
    }
}
