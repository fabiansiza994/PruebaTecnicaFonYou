package com.fmsp.fonyou.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AnswerNotFound.class)
    public ResponseEntity<String> handleAnswerNotFoundException(AnswerNotFound ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(QuestionNotFound.class)
    public ResponseEntity<String> handleQuestionNotFoundException(QuestionNotFound ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(StudentNotHaveExam.class)
    public ResponseEntity<String> handleStudentNotHaveExamException(StudentNotHaveExam ex) {
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
    }

    @ExceptionHandler(StudentNotFound.class)
    public ResponseEntity<String> handleStudentNotExistException(StudentNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ExamAlreadyAssigned.class)
    public ResponseEntity<String> handleExamAlreadyAssignedException(ExamAlreadyAssigned ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ErrorOnUpdate.class)
    public ResponseEntity<String> handleErrorOnUpdateException(ErrorOnUpdate ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ExamNotAssigned.class)
    public ResponseEntity<String> handleExamNotAssignedException(ExamNotAssigned ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ExamNotFound.class)
    public ResponseEntity<String> handleExamNotFoundException(ExamNotFound ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
