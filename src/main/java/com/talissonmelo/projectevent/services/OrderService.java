package com.talissonmelo.projectevent.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.Order;
import com.talissonmelo.projectevent.domain.Payment;
import com.talissonmelo.projectevent.domain.Ticket;
import com.talissonmelo.projectevent.domain.User;
import com.talissonmelo.projectevent.domain.enums.StatusPayment;
import com.talissonmelo.projectevent.dto.OrderDTO;
import com.talissonmelo.projectevent.repositories.OrderRepository;
import com.talissonmelo.projectevent.repositories.PaymentRepository;
import com.talissonmelo.projectevent.repositories.TicketRepository;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private EmailService emailService;

	public List<Order> findAll(){
		List<Order> list = orderRepository.findAll();
		return list;
	}
	
	public Order findById(Integer id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id) );
	}
	
	@Transactional
	public Order insert(Order obj) {
		orderRepository.save(obj);
		paymentRepository.save(obj.getPayment());
		ticketRepository.saveAll(obj.getTickets());
		return obj;

	}
	
	public Order fromDTO(OrderDTO objDTO) {
		Order obj = new Order();
		obj.setId(null);
		obj.setInstant(new Date());
		
		User user = userService.findById(objDTO.getUser());
		obj.setUser(user);
		
		Payment pay = new Payment(null, StatusPayment.AWAITING_PAYMENT, obj);
		obj.setPayment(pay);
		
		obj = orderRepository.save(obj);
		paymentRepository.save(obj.getPayment());
		
		Event event = eventService.findById(objDTO.getEvent());
		
		Ticket ticket = new Ticket(obj, event, objDTO.getAmout(), event.getPrice());

		obj.getTickets().addAll(Arrays.asList(ticket));
		
		ticketRepository.save(ticket);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
}

