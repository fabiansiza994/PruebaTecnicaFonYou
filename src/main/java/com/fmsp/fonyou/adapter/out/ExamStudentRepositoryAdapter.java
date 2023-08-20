package com.fmsp.fonyou.adapter.out;

import com.fmsp.fonyou.adapter.infrastucture.ExamStudentRepository;
import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.port.ExamStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamStudentRepositoryAdapter implements ExamStudentService {

    private final ExamStudentRepository examStudentRepository;
    private final ModelMapper modelMapper;

    public ExamStudentRepositoryAdapter(ExamStudentRepository examStudentRepository, ModelMapper modelMapper) {
        this.examStudentRepository = examStudentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExamStudentReportDto> getExamByStudentIdList(Long studentId) {
        var examList = examStudentRepository.findAllByStudentId(studentId);
        return examList.stream().map(student -> modelMapper.map(student, ExamStudentReportDto.class)).collect(Collectors.toList());
    }
}
