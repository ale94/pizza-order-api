package com.ale94.pizza_order_api.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ale94.pizza_order_api.api.models.requests.CustomerRequest;
import com.ale94.pizza_order_api.api.models.responses.CustomerResponse;
import com.ale94.pizza_order_api.domain.entities.CustomerEntity;
import com.ale94.pizza_order_api.domain.repositories.CustomerRepository;
import com.ale94.pizza_order_api.infraestructure.abstract_services.ICustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(CustomerRequest request) {
        var customerToPersist = CustomerEntity.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
        var customerPersisted = this.customerRepository.save(customerToPersist);
        log.info("Cliente con id: {} creado con exito.", customerPersisted.getId());
        return this.entityToResponse(customerPersisted);
    }

    @Override
    public CustomerResponse read(Long id) {
        var customerFromDB = this.customerRepository.findById(id).orElseThrow();
        return this.entityToResponse(customerFromDB);
    }

    @Override
    public CustomerResponse update(CustomerRequest request, Long id) {
        var customerToUpdate = this.customerRepository.findById(id).orElseThrow();
        customerToUpdate.setAddress(request.getAddress());
        customerToUpdate.setPhone(request.getPhone());
        customerToUpdate.setEmail(request.getEmail());
        var customerUpdated = this.customerRepository.save(customerToUpdate);
        log.info("Cliente con id: {} actualizado con exito.", customerUpdated.getId());
        return this.entityToResponse(customerUpdated);
    }

    @Override
    public void delete(Long id) {
        var customerToDelete = this.customerRepository.findById(id).orElseThrow();
        this.customerRepository.delete(customerToDelete);
    }

    private CustomerResponse entityToResponse(CustomerEntity customer) {
        var response = new CustomerResponse();
        BeanUtils.copyProperties(customer, response);
        return response;
    }

}
