package com.github.rodmotta.customerbackend.adapters.dtos.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class CustomerForm {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private LocalDate birthDate;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String carrer;
}
