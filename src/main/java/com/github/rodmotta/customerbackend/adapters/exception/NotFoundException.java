package com.github.rodmotta.customerbackend.adapters.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
