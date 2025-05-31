package com.ale94.pizza_order_api.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ale94.pizza_order_api.domain.entities.PizzaEntity;

public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {

}
