package com.byma.emisor.application.exception;

public class CuotaParteEsCeroException extends RuntimeException {
    public CuotaParteEsCeroException(String message) {
        super(message);
    }
}
