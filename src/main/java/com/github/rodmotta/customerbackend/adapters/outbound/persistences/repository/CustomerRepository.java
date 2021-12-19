package com.github.rodmotta.customerbackend.adapters.outbound.persistences.repository;

import com.github.rodmotta.customerbackend.adapters.exception.NotFoundException;
import com.github.rodmotta.customerbackend.adapters.outbound.persistences.entity.CustomerEntity;
import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;
import com.github.rodmotta.customerbackend.application.domain.Pagination;
import com.github.rodmotta.customerbackend.application.ports.CustomerRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public Pagination<Customer> findAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize(), Sort.Direction.valueOf(pageInfo.getDirection()), pageInfo.getSort());
        Page<CustomerEntity> customerEntities = customerRepository.findAll(pageable);
        return convertTo(customerEntities);
    }

    @Override
    public Pagination<Customer> findByCarrer(String carrer, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize(), Sort.Direction.valueOf(pageInfo.getDirection()), pageInfo.getSort());
        Page<CustomerEntity> customerEntities = customerRepository.findByCarrerContainsIgnoreCase(carrer, pageable);
        return convertTo(customerEntities);
    }

    @Override
    public Pagination<Customer> findByFirstName(String firstName, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize(), Sort.Direction.valueOf(pageInfo.getDirection()), pageInfo.getSort());
        Page<CustomerEntity> customerEntities = customerRepository.findByFirstNameContainsIgnoreCase(firstName, pageable);
        return convertTo(customerEntities);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = modelMapper.map(customer, CustomerEntity.class);
        return modelMapper.map(customerRepository.save(customerEntity), Customer.class);
    }

    @Override
    public void delete(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception){
            throw new NotFoundException("Customer not found.");
        }
    }

    private Pagination<Customer> convertTo(Page<CustomerEntity> customerPage) {

        List<Customer> customers = customerPage.getContent()
                .stream()
                .map(customerEntity -> modelMapper.map(customerEntity, Customer.class))
                .collect(Collectors.toList());

        Pagination<Customer> pagination = new Pagination<>();
        pagination.setContent(customers);
        pagination.setTotalPages(customerPage.getTotalPages());
        pagination.setTotalElements(customerPage.getTotalElements());
        pagination.setPageNumber(customerPage.getNumber());
        pagination.setNumberOfElements(customerPage.getNumberOfElements());
        pagination.setLast(customerPage.isLast());
        pagination.setFirst(customerPage.isFirst());
        return pagination;
    }
}
