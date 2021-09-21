package com.github.rodmotta.customerbackend.form;

import com.github.rodmotta.customerbackend.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
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

    public static Customer convert(CustomerRequest customerRequest) {
        return Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .birthDate(customerRequest.getBirthDate())
                .email(customerRequest.getEmail())
                .carrer(customerRequest.getCarrer())
                .build();
    }
}
