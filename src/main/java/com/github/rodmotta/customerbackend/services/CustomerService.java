package com.github.rodmotta.customerbackend.services;

import com.github.rodmotta.customerbackend.dto.CustomerResponse;
import com.github.rodmotta.customerbackend.form.CustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerResponse findById(Long id);
    Page<CustomerResponse> findByCarrer(String carrer, Pageable pageable);
    CustomerResponse save(CustomerRequest customerRequest);
    CustomerResponse update(Long id, CustomerRequest customerRequest);
    void delete(Long id);
}
