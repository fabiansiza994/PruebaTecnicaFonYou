package com.fmsp.fonyou.adapter;

import com.fmsp.fonyou.adapter.infrastucture.StudentsRepository;
import com.fmsp.fonyou.application.dto.StudentDto;
import com.fmsp.fonyou.application.port.StudentService;
import com.fmsp.fonyou.domain.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentsRepositoryAdapter implements StudentService {

    private final StudentsRepository studentsRepository;
    private final ModelMapper modelMapper;

    public StudentsRepositoryAdapter(StudentsRepository studentsRepository, ModelMapper modelMapper) {
        this.studentsRepository = studentsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        var student = studentsRepository.save(modelMapper.map(studentDto, Student.class));
        return modelMapper.map(student, StudentDto.class);
    }
}
