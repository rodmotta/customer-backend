package com.github.rodmotta.customerbackend.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationField {
    private String field;
    private String message;
}
