package com.fmsp.fonyou.adapter.out;

import com.fmsp.fonyou.adapter.infrastucture.ExamStudentRepository;
import com.fmsp.fonyou.application.dto.ExamProjection;
import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.port.ExamStudentService;
import com.fmsp.fonyou.config.exception.StudentNotHaveExam;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamStudentRepositoryAdapter implements ExamStudentService {

    private final ExamStudentRepository examStudentRepository;
    private final ModelMapper modelMapper;
    private static final String STUDENT_NOT_HAVE_EXAMS = "El estudiante no tiene examenes asignados";

    public ExamStudentRepositoryAdapter(ExamStudentRepository examStudentRepository, ModelMapper modelMapper) {
        this.examStudentRepository = examStudentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExamStudentReportDto> getExamByStudentIdList(Long studentId) {
        var examList = examStudentRepository.findAllByStudentId(studentId);
        if(examList.isEmpty())
            throw new StudentNotHaveExam(STUDENT_NOT_HAVE_EXAMS);
        return examList.stream().map(student -> modelMapper.map(student, ExamStudentReportDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<ExamStudentReportDto> getExamByStudentAndStatusTrue(Long studentId, Long examId) {
        var exam = examStudentRepository.findByStudentIdAndExamIdAndStatusTrue(studentId, examId);
        return Optional.ofNullable(modelMapper.map(exam, ExamStudentReportDto.class));
    }

    @Override
    public List<ExamProjection> getExamStudent(Long studentId, Long examId) {
        return examStudentRepository.getExamStudent(studentId, examId);
    }
}
