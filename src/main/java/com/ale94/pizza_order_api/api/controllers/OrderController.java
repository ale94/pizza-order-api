package com.ale94.pizza_order_api.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ale94.pizza_order_api.api.models.requests.OrderRequest;
import com.ale94.pizza_order_api.api.models.responses.OrderResponse;
import com.ale94.pizza_order_api.infraestructure.abstract_services.IOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> post(@RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.orderService.create(request));
    }

}
