package com.ale94.pizza_order_api.infraestructure.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ale94.pizza_order_api.api.models.requests.OrderRequest;
import com.ale94.pizza_order_api.api.models.responses.ItemOrderResponse;
import com.ale94.pizza_order_api.api.models.responses.OrderResponse;
import com.ale94.pizza_order_api.domain.entities.ItemOrderEntity;
import com.ale94.pizza_order_api.domain.entities.OrderDetailEntity;
import com.ale94.pizza_order_api.domain.entities.OrderEntity;
import com.ale94.pizza_order_api.domain.repositories.CustomerRepository;
import com.ale94.pizza_order_api.domain.repositories.ItemOrderRepository;
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
    private final ItemOrderRepository itemOrderRepository;

    @Override
    public OrderResponse create(OrderRequest request) {
        var customer = customerRepository.findById(request.getIdCustomer())
                .orElseThrow();
        var orderToPersist = OrderEntity.builder()
                .customer(customer)
                .paymentMethod(request.getPaymentMethod())
                .observations(request.getObservations())
                .orderDate(LocalDateTime.now())
                .status("pendiente")
                .deliveryAddress(customer.getAddress())
                .build();
        var orderPersisted = this.orderRepository.save(orderToPersist);
        log.info("Orden con id: {} creado con exito.", orderPersisted.getId());

        List<ItemOrderEntity> items = new ArrayList<>();
        double totalPedido = 0.0;
        int cantidadTotal = 0;
        for (var item : request.getItems()) {
            var pizza = pizzaRepository.findById(item.getIdPizza())
                    .orElseThrow();
            double subtotal = pizza.getPrice() * item.getQuantity();
            var detail = OrderDetailEntity.builder()
                    .order(orderPersisted)
                    .pizza(pizza)
                    .quantity(item.getQuantity())
                    .total(subtotal)
                    .date(LocalDateTime.now())
                    .build();
            this.orderDetailRepository.save(detail);
            totalPedido += subtotal;
            cantidadTotal += item.getQuantity();
            var itemOrder = ItemOrderEntity.builder()
                    .name("pizza: " + pizza.getName())
                    .price(pizza.getPrice())
                    .quantity(item.getQuantity())
                    .order(orderPersisted)
                    .build();
            items.add(this.itemOrderRepository.save(itemOrder));
        }
        orderPersisted.setQuantity(cantidadTotal);
        orderPersisted.setTotal(totalPedido);
        orderPersisted.setItems(items);
        this.orderRepository.save(orderPersisted);
        return this.entityToResponse(orderPersisted);
    }

    @Override
    public OrderResponse read(Long id) {
        var orderFromDB = this.orderRepository.findById(id).orElseThrow();
        return this.entityToResponse(orderFromDB);
    }

    @Override
    public OrderResponse update(OrderRequest request, Long id) {
        var orderToUpdate = this.orderRepository.findById(id).orElseThrow();
        orderToUpdate.setPaymentMethod(request.getPaymentMethod());
        orderToUpdate.setObservations(request.getObservations());
        itemOrderRepository.deleteAll(orderToUpdate.getItems());

        List<ItemOrderEntity> items = new ArrayList<>();
        double totalPedido = 0.0;
        int cantidadTotal = 0;

        for (var item : request.getItems()) {
            var pizza = pizzaRepository.findById(item.getIdPizza())
                    .orElseThrow();

            var itemOrder = ItemOrderEntity.builder()
                    .name("pizza: " + pizza.getName())
                    .price(pizza.getPrice())
                    .quantity(item.getQuantity())
                    .order(orderToUpdate)
                    .build();

            items.add(itemOrderRepository.save(itemOrder));
            totalPedido += pizza.getPrice() * item.getQuantity();
            cantidadTotal += item.getQuantity();
        }
        orderToUpdate.setTotal(totalPedido);
        orderToUpdate.setQuantity(cantidadTotal);
        orderToUpdate.setItems(items);
        var orderUpdated = orderRepository.save(orderToUpdate);
        return this.entityToResponse(orderUpdated);
    }

    @Override
    public void delete(Long id) {
        var orderToDelete = this.orderRepository.findById(id).orElseThrow();
        this.orderRepository.delete(orderToDelete);
    }

    private OrderResponse entityToResponse(OrderEntity entity) {
        var response = new OrderResponse();
        BeanUtils.copyProperties(entity, response);
        response.setCustomerName(entity.getCustomer().getName());
        List<ItemOrderResponse> itemsResponse = new ArrayList<>();
        entity.getItems().forEach(e -> {
            ItemOrderResponse itemResponse = new ItemOrderResponse();
            BeanUtils.copyProperties(e, itemResponse);
            itemsResponse.add(itemResponse);
        });
        response.setItems(itemsResponse);
        return response;
    }

}
