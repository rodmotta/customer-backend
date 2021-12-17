package com.github.rodmotta.customerbackend.adapters.inbounds.controllers;

import com.github.rodmotta.customerbackend.application.domain.Customer;
import com.github.rodmotta.customerbackend.application.domain.PageInfo;
import com.github.rodmotta.customerbackend.application.ports.CustomerServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@Api(value = "Customer API")
public class CustomerController {

    @Autowired
    private CustomerServicePort customerService;

    @ApiOperation(value = "Find a customer by id.")
    @GetMapping("{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer response = customerService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Find all customers.")
    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(@PageableDefault Pageable pageable) {
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<Customer> customers = customerService.findAll(pageInfo);
        return ResponseEntity.ok().body(new PageImpl<>(customers, pageable, customers.size()));
    }

    @ApiOperation(value = "Find customers by first name.")
    @GetMapping(params = "firstName")
    public ResponseEntity<Page<Customer>> findByFirst(@PageableDefault Pageable pageable, @RequestParam String firstName) {
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<Customer> customers = customerService.findByFirstName(firstName, pageInfo);
        return ResponseEntity.ok().body(new PageImpl<>(customers, pageable, customers.size()));
    }

    @ApiOperation(value = "Find customers by carrer.")
    @GetMapping(params = "carrer")
    public ResponseEntity<Page<Customer>> findByCarrer(@PageableDefault Pageable pageable, @RequestParam String carrer) {
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<Customer> customers = customerService.findByCarrer(carrer, pageInfo);
        return ResponseEntity.ok().body(new PageImpl<>(customers, pageable, customers.size()));
    }

    @ApiOperation(value = "Save a customer.")
    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        Customer response = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Update a customer.")
    @PutMapping("{id}")
    public ResponseEntity<Customer> save(@PathVariable Long id, @RequestBody Customer customer) {
        Customer response = customerService.update(id, customer);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Delete a customer.")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
