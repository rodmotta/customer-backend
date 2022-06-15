package com.github.rodmotta.customerbackend.application.services;

import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;
import com.github.rodmotta.customerbackend.application.domain.Pagination;
import com.github.rodmotta.customerbackend.application.ports.CustomerRepositoryPort;
import com.github.rodmotta.customerbackend.application.ports.CustomerServicePort;

public class CustomerService implements CustomerServicePort {

    private final CustomerRepositoryPort customerRepository;

    public CustomerService(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Pagination<Customer> findAll(PageInfo pageInfo) {
        return customerRepository.findAll(pageInfo);
    }

    @Override
    public Pagination<Customer> findByCarrer(String carrer, PageInfo pageInfo) {
        return customerRepository.findByCarrer(carrer, pageInfo);
    }

    @Override
    public Pagination<Customer> findByFirstName(String firstName, PageInfo pageInfo) {
        return customerRepository.findByFirstName(firstName, pageInfo);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer customerSaved = findById(id);
        customer.setId(customerSaved.getId());
        return customerRepository.save(customerSaved);
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }
}
