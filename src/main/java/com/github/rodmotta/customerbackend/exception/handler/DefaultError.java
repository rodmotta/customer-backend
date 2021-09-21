package com.github.rodmotta.customerbackend.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class DefaultError {

    private LocalDateTime timestamp;
    private int code;
    private String message;
}