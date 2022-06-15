package com.github.rodmotta.customerbackend.adapters.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI swaggerDocumentation() {
        return new OpenAPI()
                .info(setInfo());
    }

    private Info setInfo() {
        return new Info()
                .title("Customers Api")
                .description("Customers register application")
                .version("1.0")
                .contact(setContact());
    }

    private Contact setContact() {
        return new Contact()
                .name("Rodrigo Motta")
                .url("https://github.com/rodmotta/customer-backend")
                .email("rodmottacontato@gmail.com");
    }
}