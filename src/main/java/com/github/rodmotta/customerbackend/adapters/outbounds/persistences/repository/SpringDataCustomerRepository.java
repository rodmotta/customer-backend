package com.github.rodmotta.customerbackend.adapters.persistences.repository;

import com.github.rodmotta.customerbackend.adapters.persistences.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataCustomerRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByCarrerContainsIgnoreCase(String carrer);
    List<CustomerEntity> findByFirstNameContainsIgnoreCase(String firstName);
}