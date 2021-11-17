package com.github.rodmotta.customerbackend.resources;

import com.github.rodmotta.customerbackend.dto.CustomerResponse;
import com.github.rodmotta.customerbackend.form.CustomerRequest;
import com.github.rodmotta.customerbackend.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("api/customers")
@Api(value = "Customer API")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Find a customer by id.")
    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long id) {
        CustomerResponse response = customerService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Find all customers by carrer.")
    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> findByCarrer(
            @RequestParam(required = false) String carrer,
            @PageableDefault(12) Pageable pageable) {
        if (Objects.isNull(carrer)) {
            Page<CustomerResponse> response = customerService.findAll(pageable);
            return ResponseEntity.ok().body(response);
        }
        Page<CustomerResponse> response = customerService.findByCarrer(carrer, pageable);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Save a customer.")
    @PostMapping
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.save(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Update a customer.")
    @PutMapping("{id}")
    public ResponseEntity<CustomerResponse> save(@PathVariable Long id, @Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.update(id, customerRequest);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Delete a customer.")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
