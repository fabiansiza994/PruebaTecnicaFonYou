package com.fmsp.fonyou.config.exception;

public class QuestionNotFound extends RuntimeException{
    public QuestionNotFound(String mensaje) {
        super(mensaje);
    }
}
