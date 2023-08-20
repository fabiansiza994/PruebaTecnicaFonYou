package com.fmsp.fonyou.adapter.out;

import com.fmsp.fonyou.adapter.infrastucture.ExamStudentRepository;
import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.port.AssignExamService;
import com.fmsp.fonyou.domain.ExamStudentReport;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AssignExamRepositoryAdapter implements AssignExamService {

    private final ExamStudentRepository examStudentRepository;
    private final ModelMapper modelMapper;

    public AssignExamRepositoryAdapter(ExamStudentRepository examStudentRepository, ModelMapper modelMapper) {
        this.examStudentRepository = examStudentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExamStudentReportDto assignExam(ExamStudentReportDto examStudentReportDto) {
        var examAssign = examStudentRepository.save(modelMapper.map(examStudentReportDto, ExamStudentReport.class));
        return modelMapper.map(examAssign, ExamStudentReportDto.class);
    }
}
