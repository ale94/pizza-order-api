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

import com.ale94.pizza_order_api.api.models.requests.PizzaRequest;
import com.ale94.pizza_order_api.api.models.responses.PizzaResponse;
import com.ale94.pizza_order_api.infraestructure.abstract_services.IPizzaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("pizzas")
@RequiredArgsConstructor
public class PizzaController {

    private final IPizzaService pizzaService;

    @GetMapping(path = "{id}")
    public ResponseEntity<PizzaResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.pizzaService.read(id));
    }

    @PostMapping
    public ResponseEntity<PizzaResponse> post(@RequestBody PizzaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pizzaService.create(request));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PizzaResponse> update(@RequestBody PizzaRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.pizzaService.update(request, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.pizzaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
