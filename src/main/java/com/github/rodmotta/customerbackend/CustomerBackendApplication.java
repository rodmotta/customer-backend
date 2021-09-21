package com.github.rodmotta.customerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class CustomerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerBackendApplication.class, args);
	}

}
