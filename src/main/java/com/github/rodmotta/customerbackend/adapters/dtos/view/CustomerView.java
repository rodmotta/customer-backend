package com.github.rodmotta.customerbackend.adapters.dtos.view;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerView {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String carrer;
}
