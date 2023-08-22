package com.fmsp.fonyou.config.exception;

public class ErrorOnUpdate extends RuntimeException{
    public ErrorOnUpdate(String mensaje) {
        super(mensaje);
    }
}
