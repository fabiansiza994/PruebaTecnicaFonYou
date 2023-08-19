package com.fmsp.fonyou.adapter.primary;

import com.fmsp.fonyou.application.dto.StudentDto;
import com.fmsp.fonyou.application.usecase.CreateStudentUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    private final CreateStudentUseCase createStudentUseCase;

    public StudentsController(CreateStudentUseCase createStudentUseCase) {
        this.createStudentUseCase = createStudentUseCase;
    }

    @PostMapping
    public StudentDto createStudent(@RequestBody StudentDto studentDto){
        return createStudentUseCase.createStudent(studentDto);
    }
}
