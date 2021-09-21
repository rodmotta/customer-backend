package com.github.rodmotta.customerbackend.exception.handler;

import com.github.rodmotta.customerbackend.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<DefaultError> handler(NotFoundException exception) {
        DefaultError error = new DefaultError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultError> handler(MethodArgumentNotValidException exception) {
        ValidationError validation = new ValidationError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation error");
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        for(FieldError error: errors){
            validation.addError(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validation);
    }
}
