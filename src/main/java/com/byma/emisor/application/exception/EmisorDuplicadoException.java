package com.byma.emisor.application.exception;

public class EmisorDuplicadoException extends Exception {
    public EmisorDuplicadoException(String message) {
        super(message);
    }

    public EmisorDuplicadoException() {
        super();
    }
}
