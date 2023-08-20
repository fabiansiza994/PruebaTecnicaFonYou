package com.fmsp.fonyou.adapter;

import com.fmsp.fonyou.adapter.infrastucture.QuestionRepository;
import com.fmsp.fonyou.application.dto.QuestionDto;
import com.fmsp.fonyou.application.port.QuestionService;
import com.fmsp.fonyou.domain.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class QuestionRepositoryAdapter implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public QuestionRepositoryAdapter(QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        var question = questionRepository.save(modelMapper.map(questionDto, Question.class));
        return modelMapper.map(question, QuestionDto.class);
    }
}
