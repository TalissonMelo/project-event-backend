package com.talissonmelo.projectevent.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Payment;
import com.talissonmelo.projectevent.dto.UpdateStatusDTO;
import com.talissonmelo.projectevent.repositories.PaymentRepository;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	@Autowired
	private EmailService emailService;
	
	public Payment findById(Integer id) {
		Optional<Payment> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pagamento não encontrado: Id : " + id) );
	}
	
	public Payment update(Integer id ,UpdateStatusDTO dto) {
		Payment entity = repository.getOne(id);
		entity.setStatus(dto.getStatus());
		emailService.sendOrderConfirmationHtmlEmail(entity);
		return repository.save(entity);
		
	}

}
