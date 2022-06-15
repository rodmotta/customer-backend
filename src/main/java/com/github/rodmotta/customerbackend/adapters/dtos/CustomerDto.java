package com.github.rodmotta.customerbackend.adapters.dtos;

import com.github.rodmotta.customerbackend.application.domain.Customer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String carrer;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.birthDate = customer.getBirthDate();
        this.carrer = customer.getCarrer();
        this.email = customer.getEmail();
    }
}
