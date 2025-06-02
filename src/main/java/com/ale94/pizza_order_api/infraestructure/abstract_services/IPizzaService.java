package com.ale94.pizza_order_api.infraestructure.abstract_services;

import com.ale94.pizza_order_api.api.models.requests.PizzaRequest;
import com.ale94.pizza_order_api.api.models.responses.PizzaResponse;

public interface IPizzaService extends CrudService<PizzaRequest, PizzaResponse, Long> {

}
