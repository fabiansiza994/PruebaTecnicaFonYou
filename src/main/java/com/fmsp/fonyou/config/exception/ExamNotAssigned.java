package com.fmsp.fonyou.config.exception;

public class ExamNotAssigned extends RuntimeException{
    public ExamNotAssigned(String mensaje) {
        super(mensaje);
    }
}
