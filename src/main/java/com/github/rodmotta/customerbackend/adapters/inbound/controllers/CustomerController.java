package com.github.rodmotta.customerbackend.adapters.inbound.controllers;

import com.github.rodmotta.customerbackend.adapters.dtos.form.CustomerForm;
import com.github.rodmotta.customerbackend.adapters.dtos.view.CustomerView;
import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;
import com.github.rodmotta.customerbackend.application.domain.Pagination;
import com.github.rodmotta.customerbackend.application.ports.CustomerServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/customers")
@CrossOrigin(origins = "*")
@Api(value = "Customer API")
public class CustomerController {

    @Autowired
    private CustomerServicePort customerService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Find a customer by id.")
    @GetMapping("{id}")
    public ResponseEntity<CustomerView> findById(@PathVariable Long id) {
        Customer response = customerService.findById(id);
        return ResponseEntity.ok().body(toView(response));
    }

    @ApiOperation(value = "Find all customers.")
    @GetMapping
    public ResponseEntity<Pagination<CustomerView>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        PageInfo pageInfo = new PageInfo(page, size, sort, direction.toUpperCase());
        Pagination<Customer> customers = customerService.findAll(pageInfo);
        return ResponseEntity.ok().body(toView(customers));
    }

    @ApiOperation(value = "Find customers by first name.")
    @GetMapping(params = "firstName")
    public ResponseEntity<Pagination<CustomerView>> findByFirst(
            @RequestParam String firstName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        PageInfo pageInfo = new PageInfo(page, size, sort, direction.toUpperCase());
        Pagination<Customer> customers = customerService.findByFirstName(firstName, pageInfo);
        return ResponseEntity.ok().body(toView(customers));
    }

    @ApiOperation(value = "Find customers by carrer.")
    @GetMapping(params = "carrer")
    public ResponseEntity<Pagination<CustomerView>> findByCarrer(
            @RequestParam String carrer,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        PageInfo pageInfo = new PageInfo(page, size, sort, direction.toUpperCase());
        Pagination<Customer> customers = customerService.findByCarrer(carrer, pageInfo);
        return ResponseEntity.ok().body(toView(customers));
    }

    @ApiOperation(value = "Save a customer.")
    @PostMapping
    public ResponseEntity<CustomerView> save(@RequestBody @Valid CustomerForm customerForm) {
        Customer customer = customerService.save(toModel(customerForm));
        return ResponseEntity.status(HttpStatus.CREATED).body(toView(customer));
    }

    @ApiOperation(value = "Update a customer.")
    @PutMapping("{id}")
    public ResponseEntity<CustomerView> update(@PathVariable Long id, @RequestBody @Valid CustomerForm customerForm) {
        Customer customer = customerService.update(id, toModel(customerForm));
        return ResponseEntity.ok().body(toView(customer));
    }

    @ApiOperation(value = "Delete a customer.")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Pagination<CustomerView> toView(Pagination<Customer> customerPagination){
        return modelMapper.map(customerPagination, Pagination.class);
    }

    private CustomerView toView(Customer customer){
        return modelMapper.map(customer, CustomerView.class);
    }

    private Customer toModel(CustomerForm customerForm){
        return modelMapper.map(customerForm, Customer.class);
    }
}