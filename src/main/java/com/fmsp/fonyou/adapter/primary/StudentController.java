package com.fmsp.fonyou.adapter.primary;

import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.dto.StudentDto;
import com.fmsp.fonyou.application.usecase.AssignExamUseCase;
import com.fmsp.fonyou.application.usecase.CreateStudentUseCase;
import com.fmsp.fonyou.application.usecase.GetAllStudentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final CreateStudentUseCase createStudentUseCase;
    private final AssignExamUseCase assignExamUseCase;
    private final GetAllStudentUseCase getAllStudentUseCase;

    public StudentController(CreateStudentUseCase createStudentUseCase, AssignExamUseCase assignExamUseCase, GetAllStudentUseCase getAllStudentUseCase) {
        this.createStudentUseCase = createStudentUseCase;
        this.assignExamUseCase = assignExamUseCase;
        this.getAllStudentUseCase = getAllStudentUseCase;
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        var student = createStudentUseCase.createStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PostMapping("/assign_exam_to_student")
    public ResponseEntity<ExamStudentReportDto> assignExamStudent(@RequestBody ExamStudentReportDto examStudentReportDto){
        var examAssign = assignExamUseCase.AssignExam(examStudentReportDto);
        return new ResponseEntity<>(examAssign, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudent(){
        var studentList = getAllStudentUseCase.getAllStudent();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }
}
