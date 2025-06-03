package com.ale94.pizza_order_api.api.models.requests;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemOrder implements Serializable {
    private Long idPizza;
    private Integer quantity;
}
