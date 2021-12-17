package com.github.rodmotta.customerbackend.adapters.outbounds.persistences.repository;

import com.github.rodmotta.customerbackend.adapters.outbounds.persistences.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Page<CustomerEntity> findByCarrerContainsIgnoreCase(String carrer, Pageable pageable);
    Page<CustomerEntity> findByFirstNameContainsIgnoreCase(String firstName, Pageable pageable);
}