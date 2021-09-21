package com.github.rodmotta.customerbackend.repositories;

import com.github.rodmotta.customerbackend.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findByCarrerContainsIgnoreCase(String carrer, Pageable pageable);
}