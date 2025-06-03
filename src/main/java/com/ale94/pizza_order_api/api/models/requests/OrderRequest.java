package com.ale94.pizza_order_api.api.models.requests;

import java.io.Serializable;
import java.util.List;

import com.ale94.pizza_order_api.util.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest implements Serializable {

    private String idCustomer;
    private PaymentMethod paymentMethod;
    private String observations;
    private List<ItemOrder> items;
}
