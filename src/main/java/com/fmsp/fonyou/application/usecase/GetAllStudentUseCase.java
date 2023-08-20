package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.StudentDto;
import com.fmsp.fonyou.application.port.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllStudentUseCase {
    private final StudentService studentService;

    public GetAllStudentUseCase(StudentService studentService) {
        this.studentService = studentService;
    }

    public List<StudentDto> getAllStudent(){
        return studentService.getAllStudents();
    }
}
