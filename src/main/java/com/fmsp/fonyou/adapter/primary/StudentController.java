package com.fmsp.fonyou.adapter.primary;

import com.fmsp.fonyou.application.dto.StudentDto;
import com.fmsp.fonyou.application.usecase.CreateStudentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final CreateStudentUseCase createStudentUseCase;

    public StudentController(CreateStudentUseCase createStudentUseCase) {
        this.createStudentUseCase = createStudentUseCase;
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        var student = createStudentUseCase.createStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
}
