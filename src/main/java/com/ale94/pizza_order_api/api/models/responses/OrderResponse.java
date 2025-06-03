package com.ale94.pizza_order_api.api.models.responses;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.ale94.pizza_order_api.util.PaymentMethod;
import com.ale94.pizza_order_api.util.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse implements Serializable {

    private Long id;
    private LocalDateTime orderDate;
    private Status status;
    private PaymentMethod paymentMethod;
    private String deliveryAddress;
    private String observations;
    private CustomerResponse customer;
    private List<PizzaResponse> pizzas;
    private Integer quantity;
    private Double total;

}
