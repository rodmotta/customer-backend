package com.github.rodmotta.customerbackend;

import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.ports.CustomerRepositoryPort;
import com.github.rodmotta.customerbackend.application.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    CustomerRepositoryPort customerRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer(1L, "Rodrigo", "Motta",
                LocalDate.now(), "rodmottaads@gmail.com", "Software Developer");
    }

    @Test
    @DisplayName("Must save a customer successfully.")
    public void mustSaveACustomerSuccessfully() {
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        Customer customer = customerService.save(this.customer);
        Assertions.assertEquals(this.customer, customer);
    }

    @Test
    @DisplayName("Must update a customer successfully.")
    public void mustUpdateACustomerSuccessfully() {
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(customer);
        Customer customer = customerService.update(Mockito.anyLong(), this.customer);
        Assertions.assertEquals(this.customer, customer);
    }

    @Test
    @DisplayName("Must delete a customer successfully.")
    public void mustDeleteACustomerSuccessfully() {
        Mockito.doNothing().when(customerRepository).delete(Mockito.anyLong());
        customerService.delete(Mockito.anyLong());
        Mockito.verify(customerRepository, Mockito.times(1)).delete(Mockito.anyLong());
    }

    @Test
    @DisplayName("Must find a customer by id.")
    public void mustFindACustomerById() {
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(customer);
        Customer customer = customerService.findById(Mockito.anyLong());
        Assertions.assertEquals(this.customer, customer);
    }
}
