package com.github.rodmotta.customerbackend.application.ports;

import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;
import com.github.rodmotta.customerbackend.application.domain.Pagination;

public interface CustomerServicePort {
    Customer findById(Long id);
    Pagination<Customer> findAll(PageInfo pageInfo);
    Pagination<Customer> findByCarrer(String carrer, PageInfo pageInfo);
    Pagination<Customer> findByFirstName(String firstName, PageInfo pageInfo);
    Customer save(Customer customer);
    Customer update(Long id, Customer customer);
    void delete(Long id);
}
