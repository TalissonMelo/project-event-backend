package com.talissonmelo.projectevent.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talissonmelo.projectevent.domain.Payment;
import com.talissonmelo.projectevent.dto.UpdateStatusDTO;
import com.talissonmelo.projectevent.services.PaymentService;

@Controller
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Payment> findById(@PathVariable Integer id){
		Payment payment = service.findById(id);
		return ResponseEntity.ok().body(payment);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Payment> updateStatus(@PathVariable Integer id, @RequestBody UpdateStatusDTO dto){
		Payment payment = service.update(id, dto);
		payment.setId(id);
		return ResponseEntity.ok().body(payment);
	}
}
