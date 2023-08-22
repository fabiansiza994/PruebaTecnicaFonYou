package com.fmsp.fonyou.config.exception;

public class ExamNotFound extends RuntimeException{
    public ExamNotFound(String mensaje) {
        super(mensaje);
    }
}
