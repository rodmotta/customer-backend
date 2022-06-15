package com.github.rodmotta.customerbackend.adapters.outbound.persistences.repository;

import com.github.rodmotta.customerbackend.adapters.outbound.persistences.entity.CustomerEntity;
import com.github.rodmotta.customerbackend.adapters.validations.exceptions.NotFoundException;
import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;
import com.github.rodmotta.customerbackend.application.domain.Pagination;
import com.github.rodmotta.customerbackend.application.ports.CustomerRepositoryPort;
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

    @Override
    public Customer findById(Long id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found."));
        return convertTo(customer);
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
        CustomerEntity customerEntity = convertTo(customer);
        return convertTo(customerRepository.save(customerEntity));
    }

    @Override
    public void delete(Long id) {
        CustomerEntity customer = convertTo(findById(id));
        customerRepository.delete(customer);
    }

    private Pagination<Customer> convertTo(Page<CustomerEntity> customerPage) {
        List<Customer> customers = customerPage.getContent()
                .stream()
                .map(this::convertTo)
                .collect(Collectors.toList());

        return new Pagination<>(customers, customerPage.getTotalPages(),
                customerPage.getTotalElements(), customerPage.getNumber(), customerPage.getNumberOfElements(),
                customerPage.isFirst(), customerPage.isLast());
    }

    private Customer convertTo(CustomerEntity customerEntity) {
        return new Customer(customerEntity.getId(), customerEntity.getFirstName(), customerEntity.getLastName(),
                customerEntity.getBirthDate(), customerEntity.getEmail(), customerEntity.getCarrer());
    }

    private CustomerEntity convertTo(Customer customer) {
        return new CustomerEntity(customer.getId(), customer.getFirstName(), customer.getLastName(),
                customer.getBirthDate(), customer.getEmail(), customer.getCarrer());
    }
}
