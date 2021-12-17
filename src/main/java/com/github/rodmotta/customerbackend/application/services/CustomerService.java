package com.github.rodmotta.customerbackend.application.services;

import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;
import com.github.rodmotta.customerbackend.application.ports.CustomerRepositoryPort;
import com.github.rodmotta.customerbackend.application.ports.CustomerServicePort;

import java.util.List;

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
    public List<Customer> findByCarrer(String carrer, PageInfo pageInfo) {
        return customerRepository.findByCarrer(carrer, pageInfo);
    }

    @Override
    public List<Customer> findByFirstName(String firstName, PageInfo pageInfo) {
        return customerRepository.findByFirstName(firstName, pageInfo);
    }

    @Override
    public List<Customer> findAll(PageInfo pageInfo) {
        return customerRepository.findAll(pageInfo);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer customerSaved = findById(id);
        customerSaved.setFirstName(customer.getFirstName());
        customerSaved.setLastName(customer.getLastName());
        customerSaved.setCarrer(customer.getCarrer());
        customerSaved.setEmail(customer.getEmail());
        customerSaved.setBirthDate(customer.getBirthDate());
        return customerRepository.save(customerSaved);
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }
}
