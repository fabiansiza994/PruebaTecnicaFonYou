package com.fmsp.fonyou.adapter.primary;

import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.usecase.CreateExamUseCase;
import com.fmsp.fonyou.application.usecase.GetAllExamByStudentIdUseCase;
import com.fmsp.fonyou.application.usecase.GetAllExamUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {

    private final CreateExamUseCase createExamUseCase;
    private final GetAllExamUseCase getAllExamUseCase;
    private final GetAllExamByStudentIdUseCase getAllExamByStudentIdUseCase;

    public ExamController(CreateExamUseCase createExamUseCase, GetAllExamUseCase getAllExamUseCase, GetAllExamByStudentIdUseCase getAllExamByStudentIdUseCase) {
        this.createExamUseCase = createExamUseCase;
        this.getAllExamUseCase = getAllExamUseCase;
        this.getAllExamByStudentIdUseCase = getAllExamByStudentIdUseCase;
    }

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto){
        var exam = createExamUseCase.createExam(examDto);
        return new ResponseEntity<>(exam, HttpStatus.CREATED);
    }

     @GetMapping
    public ResponseEntity<List<ExamDto>> getAllExam(){
        var examList = getAllExamUseCase.getExamList();
         return new ResponseEntity<>(examList, HttpStatus.OK);
     }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<ExamStudentReportDto>> getAllExamByStudentId(@PathVariable Long studentId){
        var examList = getAllExamByStudentIdUseCase.getExamByStudentIdList(studentId);
        return new ResponseEntity<>(examList, HttpStatus.OK);
    }

}
