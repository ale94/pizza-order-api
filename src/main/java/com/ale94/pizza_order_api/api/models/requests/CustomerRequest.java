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
public class CustomerRequest implements Serializable {

    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;

}
