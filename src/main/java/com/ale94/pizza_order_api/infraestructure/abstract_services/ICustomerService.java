package com.ale94.pizza_order_api.infraestructure.abstract_services;

import com.ale94.pizza_order_api.api.models.requests.CustomerRequest;
import com.ale94.pizza_order_api.api.models.responses.CustomerResponse;

public interface ICustomerService extends CrudService<CustomerRequest, CustomerResponse, Long> {

}
