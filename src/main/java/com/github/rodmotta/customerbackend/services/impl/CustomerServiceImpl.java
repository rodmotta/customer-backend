package com.github.rodmotta.customerbackend.services.impl;

import com.github.rodmotta.customerbackend.domain.Customer;
import com.github.rodmotta.customerbackend.dto.CustomerResponse;
import com.github.rodmotta.customerbackend.exception.NotFoundException;
import com.github.rodmotta.customerbackend.form.CustomerRequest;
import com.github.rodmotta.customerbackend.repositories.CustomerRepository;
import com.github.rodmotta.customerbackend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponse findById(Long id) {
        return CustomerResponse.convert(customerRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Customer not found")));
    }

    @Override
    public Page<CustomerResponse> findByCarrer(String carrer, Pageable pageable) {
        Page<Customer> customers = customerRepository.findByCarrerContainsIgnoreCase(carrer, pageable);
        return customers.map(customer -> CustomerResponse.convert(customer));
    }

    @Override
    public Page<CustomerResponse> findAll(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(customer -> CustomerResponse.convert(customer));
    }

    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(CustomerRequest.convert(customerRequest));
        return CustomerResponse.convert(customer);
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Customer not found"));
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setBirthDate(customerRequest.getBirthDate());
        customer.setEmail(customerRequest.getEmail());
        customer.setCarrer(customerRequest.getCarrer());
        customerRepository.save(customer);
        return CustomerResponse.convert(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }
}
