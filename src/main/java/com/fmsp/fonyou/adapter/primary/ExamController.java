package com.fmsp.fonyou.adapter.primary;

import com.fmsp.fonyou.application.dto.*;
import com.fmsp.fonyou.application.usecase.*;
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
    private final GrandingExamUseCase grandingExamUseCase;
    private final GetExamStudentAnswerUseCase getExamStudentAnswerUseCase;

    public ExamController(CreateExamUseCase createExamUseCase, GetAllExamUseCase getAllExamUseCase,
                          GetAllExamByStudentIdUseCase getAllExamByStudentIdUseCase, GetExamStudentUseCase getExamStudentUseCase,
                          GrandingExamUseCase grandingExamUseCase, GetExamStudentAnswerUseCase getExamStudentAnswerUseCase) {
        this.createExamUseCase = createExamUseCase;
        this.getAllExamUseCase = getAllExamUseCase;
        this.getAllExamByStudentIdUseCase = getAllExamByStudentIdUseCase;
        this.getExamStudentUseCase = getExamStudentUseCase;
        this.grandingExamUseCase = grandingExamUseCase;
        this.getExamStudentAnswerUseCase = getExamStudentAnswerUseCase;
    }

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto){
        var exam = createExamUseCase.createExam(examDto);
        return new ResponseEntity<>(exam, HttpStatus.CREATED);
    }

     @GetMapping
    public ResponseEntity<List<ExamListDto>> getAllExam(){
        var examList = getAllExamUseCase.getExamList();
         return new ResponseEntity<>(examList, HttpStatus.OK);
     }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<ExamStudentAssignDto>> getAllExamByStudentId(@PathVariable Long studentId){
        var examList = getAllExamByStudentIdUseCase.getExamByStudentIdList(studentId);
        return new ResponseEntity<>(examList, HttpStatus.OK);
    }

    @GetMapping("/{studentId}/{examId}")
    public ResponseEntity<ExamDetailDto> getExamByIdAndStudentId(@PathVariable Long studentId, @PathVariable Long examId){
        var examList = getExamStudentUseCase.getExamStudent(studentId, examId);
        return new ResponseEntity<>(examList, HttpStatus.OK);
    }

    @PostMapping("/exam_student")
    public ResponseEntity<ResultExamDto> grandingExam(@RequestBody ExamDto.ExamStudentDto examStudentDto) {
        var examList = grandingExamUseCase.grandExam(examStudentDto);
        return new ResponseEntity<>(examList, HttpStatus.OK);
    }

    @GetMapping("get_exam_student_answer/{studentId}/{examId}")
    public ResponseEntity<AnswerResultDto> getExamStudentAnswer(@PathVariable Long studentId, @PathVariable Long examId){
        var examList = getExamStudentAnswerUseCase.getExamStudentAnswer(studentId, examId);
        return new ResponseEntity<>(examList, HttpStatus.OK);
    }
}
