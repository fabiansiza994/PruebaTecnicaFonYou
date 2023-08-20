package com.fmsp.fonyou.adapter.out;

import com.fmsp.fonyou.adapter.infrastucture.StudentsRepository;
import com.fmsp.fonyou.application.dto.StudentDto;
import com.fmsp.fonyou.application.port.StudentService;
import com.fmsp.fonyou.domain.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<StudentDto> getAllStudents() {
        var students = studentsRepository.findAll();
        return students.stream().map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        var student = studentsRepository.findById(studentId);
        return modelMapper.map(student, StudentDto.class);
    }
}
