package com.fmsp.fonyou.adapter.out;

import com.fmsp.fonyou.adapter.infrastucture.ExamRepository;
import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.port.ExamService;
import com.fmsp.fonyou.domain.Exam;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamRepositoryAdapter implements ExamService {

    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;

    public ExamRepositoryAdapter(ExamRepository examRepository, ModelMapper modelMapper) {
        this.examRepository = examRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExamDto createExam(ExamDto examDto) {
        var exam = examRepository.save(modelMapper.map(examDto, Exam.class));
        return modelMapper.map(exam, ExamDto.class);
    }

    @Override
    public List<ExamDto> getExamList() {
        var examList = examRepository.findAll();
        return examList.stream().map(exam -> modelMapper.map(exam, ExamDto.class)).collect(Collectors.toList());
    }
}
