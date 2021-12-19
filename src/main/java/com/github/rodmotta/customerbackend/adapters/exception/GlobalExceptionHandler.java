package com.github.rodmotta.customerbackend.adapters.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultError> defaultError(NotFoundException exception) {
        DefaultError error = new DefaultError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultError> defaultError(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = setFieldErrors(exception);
        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), "Field validation error.", fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private List<FieldError> setFieldErrors(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError -> fieldErrors.add(new FieldError(fieldError.getField(), fieldError.getDefaultMessage())));
        return fieldErrors;
    }
}
