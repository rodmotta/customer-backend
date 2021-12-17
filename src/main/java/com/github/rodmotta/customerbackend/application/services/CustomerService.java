package com.github.rodmotta.customerbackend.core.services;

import com.github.rodmotta.customerbackend.core.domain.Customer;
import com.github.rodmotta.customerbackend.core.ports.CustomerRepositoryPort;
import com.github.rodmotta.customerbackend.core.ports.CustomerServicePort;

import java.util.List;

public class CustomerService implements CustomerServicePort {

    private CustomerRepositoryPort customerRepository;

    public CustomerService(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findByCarrer(String carrer) {
        return customerRepository.findByCarrer(carrer);
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
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
