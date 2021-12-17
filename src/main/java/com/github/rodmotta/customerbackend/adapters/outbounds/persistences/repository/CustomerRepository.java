package com.github.rodmotta.customerbackend.adapters.outbounds.persistences.repository;

import com.github.rodmotta.customerbackend.adapters.outbounds.persistences.entity.CustomerEntity;
import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;
import com.github.rodmotta.customerbackend.application.domain.exception.NotFoundException;
import com.github.rodmotta.customerbackend.application.ports.CustomerRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository implements CustomerRepositoryPort {

    @Autowired
    private SpringDataCustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Customer findById(Long id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found."));
        return modelMapper.map(customer, Customer.class);
    }

    @Override
    public List<Customer> findByCarrer(String carrer, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
        Page<CustomerEntity> customerEntities = customerRepository.findByCarrerContainsIgnoreCase(carrer, pageable);
        return customerEntities.stream()
                .map(customer -> modelMapper.map(customer, Customer.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findByFirstName(String firstName, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
        Page<CustomerEntity> customerEntities = customerRepository.findByFirstNameContainsIgnoreCase(firstName, pageable);
        return customerEntities.stream()
                .map(customer -> modelMapper.map(customer, Customer.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
        Page<CustomerEntity> customerEntities = customerRepository.findAll(pageable);
        return customerEntities.stream()
                .map(customer -> modelMapper.map(customer, Customer.class))
                .collect(Collectors.toList());
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = modelMapper.map(customer, CustomerEntity.class);
        return modelMapper.map(customerRepository.save(customerEntity), Customer.class);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
