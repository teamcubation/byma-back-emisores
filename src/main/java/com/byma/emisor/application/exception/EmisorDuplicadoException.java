package com.byma.emisor.application.exception;

public class EmisorDuplicadoException extends RuntimeException {
    public EmisorDuplicadoException(String message) {
        super(message);
    }

    public EmisorDuplicadoException() {
        super();
    }
}
