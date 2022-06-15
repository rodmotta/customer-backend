package com.github.rodmotta.customerbackend.adapters.forms;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class CustomerForm {
    @NotEmpty(message = "Must not be empty.")
    private String firstName;
    @NotEmpty(message = "Must not be empty.")
    private String lastName;
    private LocalDate birthDate;
    @NotEmpty(message = "Must not be empty.")
    @Email(message = "Must be a valid email address.")
    private String email;
    @NotEmpty(message = "Must not be empty.")
    private String carrer;
}
