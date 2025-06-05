package com.ale94.pizza_order_api.infraestructure.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ale94.pizza_order_api.api.models.requests.ItemOrderRequest;
import com.ale94.pizza_order_api.api.models.requests.OrderRequest;
import com.ale94.pizza_order_api.api.models.responses.ItemOrderResponse;
import com.ale94.pizza_order_api.api.models.responses.OrderResponse;
import com.ale94.pizza_order_api.domain.entities.OrderDetailEntity;
import com.ale94.pizza_order_api.domain.entities.OrderEntity;
import com.ale94.pizza_order_api.domain.repositories.CustomerRepository;
import com.ale94.pizza_order_api.domain.repositories.OrderDetailRepository;
import com.ale94.pizza_order_api.domain.repositories.OrderRepository;
import com.ale94.pizza_order_api.domain.repositories.PizzaRepository;
import com.ale94.pizza_order_api.infraestructure.abstract_services.IOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PizzaRepository pizzaRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderResponse create(OrderRequest request) {
        var customer = customerRepository.findById(request.getIdCustomer())
                .orElseThrow();
        var order = OrderEntity.builder()
                .customer(customer)
                .paymentMethod(request.getPaymentMethod())
                .observations(request.getObservations())
                .orderDate(LocalDateTime.now())
                .status("pendiente")
                .deliveryAddress(customer.getAddress())
                .build();
        order = this.orderRepository.save(order);

        List<ItemOrderResponse> items = new ArrayList<>();
        double totalPedido = 0.0;
        int cantidadTotal = 0;
        for (var item : request.getItems()) {
            var pizza = pizzaRepository.findById(item.getIdPizza())
                    .orElseThrow();
            double subtotal = pizza.getPrice() * item.getQuantity();
            var detail = OrderDetailEntity.builder()
                    .order(order)
                    .pizza(pizza)
                    .quantity(item.getQuantity())
                    .total(subtotal)
                    .date(LocalDateTime.now())
                    .build();
            this.orderDetailRepository.save(detail);
            totalPedido += subtotal;
            cantidadTotal += item.getQuantity();
            var itemOrder = ItemOrderResponse.builder()
                    .name("pizza: " + pizza.getName())
                    .price(pizza.getPrice())
                    .quantity(item.getQuantity())
                    .build();
            items.add(itemOrder);
        }
        order.setQuantity(cantidadTotal);
        order.setTotal(totalPedido);
        this.orderRepository.save(order);
        return this.entityToResponse(order, items);
    }

    @Override
    public OrderResponse read(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public OrderResponse update(OrderRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private OrderResponse entityToResponse(OrderEntity entity, List<ItemOrderResponse> items) {
        var response = new OrderResponse();
        BeanUtils.copyProperties(entity, response);
        response.setCustomerName(entity.getCustomer().getName());
        response.setItems(items);
        return response;
    }

}
