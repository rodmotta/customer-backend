package com.github.rodmotta.customerbackend.adapters.configurations;

import com.github.rodmotta.customerbackend.application.ports.CustomerRepositoryPort;
import com.github.rodmotta.customerbackend.application.ports.CustomerServicePort;
import com.github.rodmotta.customerbackend.application.services.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CustomerServicePort customerService(CustomerRepositoryPort customerRepository) {
        return new CustomerService(customerRepository);
    }
}
