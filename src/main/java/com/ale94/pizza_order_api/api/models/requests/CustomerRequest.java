package com.ale94.pizza_order_api.api.models.requests;

import java.io.Serializable;

public class CustomerRequest implements Serializable {

    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;

}
