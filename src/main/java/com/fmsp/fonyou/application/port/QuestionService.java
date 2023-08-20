package com.fmsp.fonyou.application.port;

import com.fmsp.fonyou.application.dto.QuestionDto;

public interface QuestionService {
    QuestionDto createQuestion(QuestionDto questionDto);
}
