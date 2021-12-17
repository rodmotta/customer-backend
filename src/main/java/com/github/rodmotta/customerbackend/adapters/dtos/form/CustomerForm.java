package com.github.rodmotta.customerbackend.application.dto.form;

import com.github.rodmotta.customerbackend.application.persistence.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
