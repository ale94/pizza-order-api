package com.ale94.pizza_order_api.infraestructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ale94.pizza_order_api.api.models.requests.PizzaRequest;
import com.ale94.pizza_order_api.api.models.responses.CustomerResponse;
import com.ale94.pizza_order_api.api.models.responses.PizzaResponse;
import com.ale94.pizza_order_api.domain.entities.CustomerEntity;
import com.ale94.pizza_order_api.domain.entities.PizzaEntity;
import com.ale94.pizza_order_api.domain.repositories.PizzaRepository;
import com.ale94.pizza_order_api.infraestructure.abstract_services.IPizzaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class PizzaService implements IPizzaService {

    private final PizzaRepository pizzaRepository;

    @Override
    public PizzaResponse create(PizzaRequest request) {
        var pizzaToPersist = PizzaEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .available(true)
                .build();
        var pizzaPersisted = this.pizzaRepository.save(pizzaToPersist);
        log.info("Pizza con id: {} creada con exito.", pizzaPersisted.getName());
        return this.entityToResponse(pizzaPersisted);
    }

    @Override
    public PizzaResponse read(Long id) {
        var pizzaFromDB = this.pizzaRepository.findById(id).orElseThrow();
        return this.entityToResponse(pizzaFromDB);
    }

    @Override
    public PizzaResponse update(PizzaRequest request, Long id) {
        var pizzaToUpdate = this.pizzaRepository.findById(id).orElseThrow();
        pizzaToUpdate.setName(request.getName());
        pizzaToUpdate.setPrice(request.getPrice());
        pizzaToUpdate.setDescription(request.getDescription());
        var pizzaUpdated = this.pizzaRepository.save(pizzaToUpdate);
        log.info("Pizza con id: {} actualizada con exito.", pizzaUpdated.getId());
        return this.entityToResponse(pizzaUpdated);
    }

    @Override
    public void delete(Long id) {
        var pizzaToDelete = this.pizzaRepository.findById(id).orElseThrow();
        this.pizzaRepository.delete(pizzaToDelete);
    }

    private PizzaResponse entityToResponse(PizzaEntity pizza) {
        var response = new PizzaResponse();
        BeanUtils.copyProperties(pizza, response);
        return response;
    }

}
