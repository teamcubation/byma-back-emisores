package com.byma.emisor.application.exception;

public class EmisorNoEncontradoException extends RuntimeException {
    public EmisorNoEncontradoException(String message) {
        super(message);
    }

    public EmisorNoEncontradoException() {
        super();
    }
}
