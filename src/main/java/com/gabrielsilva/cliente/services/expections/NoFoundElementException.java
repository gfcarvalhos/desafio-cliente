package com.gabrielsilva.cliente.services.expections;

public class NoFoundElementException extends RuntimeException {
    public NoFoundElementException(String msg) {
        super(msg);
    }
}
