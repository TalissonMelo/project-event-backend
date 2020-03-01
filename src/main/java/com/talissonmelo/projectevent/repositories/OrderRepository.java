package com.talissonmelo.projectevent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talissonmelo.projectevent.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
