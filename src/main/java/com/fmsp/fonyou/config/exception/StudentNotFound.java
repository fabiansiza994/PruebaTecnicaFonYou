package com.fmsp.fonyou.config.exception;

public class StudentNotFound extends RuntimeException{

    public StudentNotFound(String mensaje) {
        super(mensaje);
    }
}
