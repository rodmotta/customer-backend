package com.github.rodmotta.customerbackend.adapters.inbound.controllers;

import com.github.rodmotta.customerbackend.adapters.dtos.CustomerDto;
import com.github.rodmotta.customerbackend.adapters.forms.CustomerForm;
import com.github.rodmotta.customerbackend.adapters.validations.DefaultError;
import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;
import com.github.rodmotta.customerbackend.application.domain.Pagination;
import com.github.rodmotta.customerbackend.application.ports.CustomerServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerServicePort customerService;

    @Operation(summary = "Find a customer by id.", description = "Find a customer by id.", tags = "Customer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully performed."),
        @ApiResponse(responseCode = "404", description = "Customer not found.", content = {
            @Content(schema = @Schema(implementation = DefaultError.class))})
    })
    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
        Customer response = customerService.findById(id);
        return ResponseEntity.ok().body(convertTo(response));
    }

    @Operation(summary = "Find all customers.", description = "Find all customers.", tags = "Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully performed.")
    })
    @GetMapping
    public ResponseEntity<Pagination<CustomerDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        PageInfo pageInfo = new PageInfo(page, size, sort, direction.toUpperCase());
        Pagination<Customer> customers = customerService.findAll(pageInfo);
        return ResponseEntity.ok().body(convertTo(customers));
    }

    @Operation(summary = "Find customers by first name.", description = "Find customers by first name.", tags = "Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully performed.")
    })
    @GetMapping(params = "firstName")
    public ResponseEntity<Pagination<CustomerDto>> findByFirst(
            @RequestParam String firstName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        PageInfo pageInfo = new PageInfo(page, size, sort, direction.toUpperCase());
        Pagination<Customer> customers = customerService.findByFirstName(firstName, pageInfo);
        return ResponseEntity.ok().body(convertTo(customers));
    }

    @Operation(summary = "Find customers by carrer.", description = "Find customers by carrer.", tags = "Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully performed.")
    })
    @GetMapping(params = "carrer")
    public ResponseEntity<Pagination<CustomerDto>> findByCarrer(
            @RequestParam String carrer,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        PageInfo pageInfo = new PageInfo(page, size, sort, direction.toUpperCase());
        Pagination<Customer> customers = customerService.findByCarrer(carrer, pageInfo);
        return ResponseEntity.ok().body(convertTo(customers));
    }

    @Operation(summary = "Save a customer.", description = "Save a customer.", tags = "Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully performed."),
            @ApiResponse(responseCode = "400", description = "Validation error.", content = {
                    @Content(schema = @Schema(implementation = DefaultError.class))})
    })
    @PostMapping
    public ResponseEntity<CustomerDto> save(@RequestBody @Valid CustomerForm customerForm) {
        Customer customer = customerService.save(convertTo(customerForm));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertTo(customer));
    }

    @Operation(summary = "Update a customer by id.", description = "Update a customer by id.", tags = "Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully performed."),
            @ApiResponse(responseCode = "400", description = "Validation error.", content = {
                    @Content(schema = @Schema(implementation = DefaultError.class))})
    })
    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Long id, @RequestBody @Valid CustomerForm customerForm) {
        Customer customer = customerService.update(id, convertTo(customerForm));
        return ResponseEntity.ok().body(convertTo(customer));
    }

    @Operation(summary = "Delete a customer by id.", description = "Delete a customer by id.", tags = "Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully performed."),
            @ApiResponse(responseCode = "404", description = "Customer not found.", content = {
                    @Content(schema = @Schema(implementation = DefaultError.class))})
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Pagination<CustomerDto> convertTo(Pagination<Customer> customerPagination){
        List<CustomerDto> customersDto = customerPagination.getContent().stream()
                .map(this::convertTo)
                .collect(Collectors.toList());

        return new Pagination<>(customersDto, customerPagination.getTotalPages(),
                customerPagination.getTotalElements(), customerPagination.getPageNumber(),
                customerPagination.getPageNumber(), customerPagination.isFirst(), customerPagination.isLast());
    }

    private CustomerDto convertTo(Customer customer){
        return new CustomerDto(customer);
    }

    private Customer convertTo(CustomerForm customerForm){
        return new Customer(null, customerForm.getFirstName(), customerForm.getLastName(), customerForm.getBirthDate(),
                customerForm.getEmail(), customerForm.getCarrer());
    }
}