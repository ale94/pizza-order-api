package com.ale94.pizza_order_api.api.models.responses;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;
    private String status;
    private String paymentMethod;
    private String deliveryAddress;
    private String observations;
    private String customerName;
    private List<ItemOrderResponse> items;
    private Integer quantity;
    private Double total;

}
