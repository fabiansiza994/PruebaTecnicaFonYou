package com.fmsp.fonyou.adapter;

import com.fmsp.fonyou.adapter.infrastucture.AnswerRepository;
import com.fmsp.fonyou.application.dto.AnswerDto;
import com.fmsp.fonyou.application.port.AnswerService;
import com.fmsp.fonyou.domain.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AnswerRepositoryAdapter implements AnswerService {

    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    public AnswerRepositoryAdapter(AnswerRepository answerRepository, ModelMapper modelMapper) {
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AnswerDto createAnswer(AnswerDto answerDto) {
        var answer = answerRepository.save(modelMapper.map(answerDto, Answer.class));
       return modelMapper.map(answer, AnswerDto.class);
    }
}
