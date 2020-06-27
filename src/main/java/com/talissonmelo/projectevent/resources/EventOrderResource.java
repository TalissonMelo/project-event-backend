package com.talissonmelo.projectevent.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.Order;
import com.talissonmelo.projectevent.dto.ParticipantsViewDTO;
import com.talissonmelo.projectevent.services.EventService;

@RestController
@RequestMapping(value = "/events/{eventId}/orders")
public class EventOrderResource {

	@Autowired
	private EventService service;
	
	@GetMapping
	public List<ParticipantsViewDTO> findAll(@PathVariable Integer eventId) {
		Event event = service.findById(eventId);
		List<ParticipantsViewDTO> participants = toCollectionModel(event.getOrders());
		return participants;
	}
	
	private ParticipantsViewDTO toModel(Order order) {
		ParticipantsViewDTO participantsViewDTO = new ParticipantsViewDTO();
		participantsViewDTO.setPrice(order.getValueTotal());
		participantsViewDTO.setTicketsId(order.getPayment().getId());
		participantsViewDTO.setStatus(order.getPayment().getStatus());
		participantsViewDTO.setNameUser(order.getUser().getName());
		participantsViewDTO.setEmailUser(order.getUser().getEmail());
		participantsViewDTO.setPhoneUser(order.getUser().getPhone());
		return participantsViewDTO;
	}

	private List<ParticipantsViewDTO> toCollectionModel(List<Order> orders) {
		return orders.stream().map(order -> toModel(order)).collect(Collectors.toList());
	}
}
