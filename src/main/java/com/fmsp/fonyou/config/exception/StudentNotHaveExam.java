package com.fmsp.fonyou.config.exception;

public class StudentNotHaveExam extends RuntimeException{
    public StudentNotHaveExam(String mensaje) {
        super(mensaje);
    }
}
