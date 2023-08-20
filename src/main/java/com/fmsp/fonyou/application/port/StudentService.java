package com.fmsp.fonyou.application.port;

import com.fmsp.fonyou.application.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long studentId);
}
