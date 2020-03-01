package com.talissonmelo.projectevent.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Order;
import com.talissonmelo.projectevent.repositories.OrderRepository;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	public List<Order> findAll(){
		List<Order> list = orderRepository.findAll();
		return list;
	}
	
	public Order findById(Integer id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id) );
	}
}
