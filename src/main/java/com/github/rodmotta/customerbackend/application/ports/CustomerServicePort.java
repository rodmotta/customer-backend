package com.github.rodmotta.customerbackend.core.ports;

import com.github.rodmotta.customerbackend.core.domain.Customer;

import java.util.List;

public interface CustomerServicePort {
    Customer findById(Long id);
    List<Customer> findAll();
    List<Customer> findByCarrer(String carrer);
    List<Customer> findByFirstName(String firstName);
    Customer save(Customer customer);
    Customer update(Long id, Customer customer);
    void delete(Long id);
}
