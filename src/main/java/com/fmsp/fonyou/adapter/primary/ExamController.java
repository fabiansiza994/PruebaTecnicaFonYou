package com.fmsp.fonyou.adapter.primary;

import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.usecase.CreateExamUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exam")
public class ExamController {

    private final CreateExamUseCase createExamUseCase;

    public ExamController(CreateExamUseCase createExamUseCase) {
        this.createExamUseCase = createExamUseCase;
    }

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto){
        var exam = createExamUseCase.createExam(examDto);
        return new ResponseEntity<>(exam, HttpStatus.CREATED);
    }
}
