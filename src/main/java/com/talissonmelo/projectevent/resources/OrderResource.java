package com.talissonmelo.projectevent.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.talissonmelo.projectevent.domain.Order;
import com.talissonmelo.projectevent.dto.OrderDTO;
import com.talissonmelo.projectevent.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = orderService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Integer id){
		Order obj = orderService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Order> insert(@Valid @RequestBody OrderDTO objDTO) {
		Order obj = orderService.fromDTO(objDTO);
		orderService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
