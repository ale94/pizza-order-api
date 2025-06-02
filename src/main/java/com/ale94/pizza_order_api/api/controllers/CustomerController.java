package com.ale94.pizza_order_api.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ale94.pizza_order_api.api.models.requests.CustomerRequest;
import com.ale94.pizza_order_api.api.models.responses.CustomerResponse;
import com.ale94.pizza_order_api.domain.entities.CustomerEntity;
import com.ale94.pizza_order_api.infraestructure.abstract_services.ICustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping(path = "{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.customerService.read(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> post(@RequestBody CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.customerService.create(request));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<CustomerResponse> update(@RequestBody CustomerRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.customerService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
