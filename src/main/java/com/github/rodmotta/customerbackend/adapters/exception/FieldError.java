package com.github.rodmotta.customerbackend.adapters.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldError {
    private String field;
    private String message;
}
