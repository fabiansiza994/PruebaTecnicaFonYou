package com.fmsp.fonyou.application.port;

import com.fmsp.fonyou.application.dto.AnswerDto;

public interface AnswerService {
    AnswerDto createAnswer(AnswerDto answerDto);
    AnswerDto findCorrectAnswer(Long questionId, Long answerId);
}
