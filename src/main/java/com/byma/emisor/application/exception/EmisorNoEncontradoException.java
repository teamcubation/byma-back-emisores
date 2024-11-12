package com.byma.emisor.application.exception;

public class EmisorNoEncontradoException extends Exception {
    public EmisorNoEncontradoException(String message) {
        super(message);
    }

    public EmisorNoEncontradoException() {
        super();
    }
}
