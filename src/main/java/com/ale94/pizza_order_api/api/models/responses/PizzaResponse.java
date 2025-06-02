package com.ale94.pizza_order_api.api.models.responses;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PizzaResponse implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private Boolean available;

}
