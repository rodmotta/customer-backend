package com.github.rodmotta.customerbackend.adapters.validations.handlers;

import com.github.rodmotta.customerbackend.adapters.validations.DefaultError;
import com.github.rodmotta.customerbackend.adapters.validations.FieldError;
import com.github.rodmotta.customerbackend.adapters.validations.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultError> notfound(NotFoundException exception) {
        DefaultError error = new DefaultError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultError> validation(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors().stream().map(fieldError ->
            new FieldError(fieldError.getField(), fieldError.getDefaultMessage())
        ).collect(Collectors.toList());
        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), "Field validation error.", fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
