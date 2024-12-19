package com.byma.emisor.application.exception.billetera;

public class BilleteraNoEncontradoException extends Exception {
    public BilleteraNoEncontradoException(String message) {
        super(message);
    }

    public BilleteraNoEncontradoException() {
        super();
    }
}
