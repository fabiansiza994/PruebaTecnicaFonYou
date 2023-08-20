package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.StudentDto;
import com.fmsp.fonyou.application.port.StudentService;
import org.springframework.stereotype.Service;

@Service
public class GetStudentByIdUseCase {

    private final StudentService studentService;

    public GetStudentByIdUseCase(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentDto getStudentById(Long studentId){
        return studentService.getStudentById(studentId);
    }
}
