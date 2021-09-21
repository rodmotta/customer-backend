package com.github.rodmotta.customerbackend.exception.handler;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends DefaultError {

    private List<ValidationField> errors = new ArrayList<>();

    public ValidationError(LocalDateTime timestamp, int code, String message) {
        super(timestamp, code, message);
    }

    public void addError(String field, String message) {
        errors.add(new ValidationField(field, message));
    }
}
