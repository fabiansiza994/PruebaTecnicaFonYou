package com.fmsp.fonyou.adapter.primary;

import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.dto.ExamProjection;
import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.usecase.CreateExamUseCase;
import com.fmsp.fonyou.application.usecase.GetAllExamByStudentIdUseCase;
import com.fmsp.fonyou.application.usecase.GetAllExamUseCase;
import com.fmsp.fonyou.application.usecase.GetExamStudentUseCase;
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
    private final GetExamStudentUseCase getExamStudentUseCase;

    public ExamController(CreateExamUseCase createExamUseCase, GetAllExamUseCase getAllExamUseCase,
                          GetAllExamByStudentIdUseCase getAllExamByStudentIdUseCase, GetExamStudentUseCase getExamStudentUseCase) {
        this.createExamUseCase = createExamUseCase;
        this.getAllExamUseCase = getAllExamUseCase;
        this.getAllExamByStudentIdUseCase = getAllExamByStudentIdUseCase;
        this.getExamStudentUseCase = getExamStudentUseCase;
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

    @GetMapping("/{studentId}/{examId}")
    public ResponseEntity<List<ExamProjection>> getExamByIdAndStudentId(@PathVariable Long studentId, @PathVariable Long examId){
        var examList = getExamStudentUseCase.getExamStudent(studentId, examId);
        return new ResponseEntity<>(examList, HttpStatus.OK);
    }

    @PostMapping("/exam_student")
    public ResponseEntity<ExamDto.ExamStudentDto> examStudent(@RequestBody ExamDto.ExamStudentDto examStudentDto) {
        var examList = examStudentDto;
        return new ResponseEntity<>(examList, HttpStatus.OK);
    }
}
