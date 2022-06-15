package com.github.rodmotta.customerbackend.adapters.validations.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
