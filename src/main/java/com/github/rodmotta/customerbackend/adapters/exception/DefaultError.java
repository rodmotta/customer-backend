package com.github.rodmotta.customerbackend.adapters.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultError {
    private int code;
    private String message;
    private List<FieldError> fieldErrors;
}
