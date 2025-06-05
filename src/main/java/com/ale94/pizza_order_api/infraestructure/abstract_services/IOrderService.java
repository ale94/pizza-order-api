package com.ale94.pizza_order_api.infraestructure.abstract_services;

import com.ale94.pizza_order_api.api.models.requests.OrderRequest;
import com.ale94.pizza_order_api.api.models.responses.OrderResponse;

public interface IOrderService extends CrudService<OrderRequest, OrderResponse, Long>{


}
