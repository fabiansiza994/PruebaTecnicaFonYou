package com.fmsp.fonyou.config.exception;

public class ExamAlreadyAssigned extends RuntimeException{
    public ExamAlreadyAssigned(String mensaje) {
        super(mensaje);
    }
}
