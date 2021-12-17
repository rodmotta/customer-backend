package com.github.rodmotta.customerbackend.application.ports;

import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;

import java.util.List;

public interface CustomerServicePort {
    Customer findById(Long id);
    List<Customer> findAll(PageInfo pageInfo);
    List<Customer> findByCarrer(String carrer, PageInfo pageInfo);
    List<Customer> findByFirstName(String firstName, PageInfo pageInfo);
    Customer save(Customer customer);
    Customer update(Long id, Customer customer);
    void delete(Long id);
}
