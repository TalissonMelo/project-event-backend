package com.talissonmelo.projectevent.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.Order;
import com.talissonmelo.projectevent.dto.ParticipantsViewDTO;
import com.talissonmelo.projectevent.services.EventReportService;
import com.talissonmelo.projectevent.services.EventService;

@RestController
@RequestMapping(value = "/events/{eventId}/orders")
public class EventOrderResource {

	@Autowired
	private EventService service;
	
	@Autowired
	private EventReportService eventReportService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ParticipantsViewDTO> findAll(@PathVariable Integer eventId) {
		Event event = service.findById(eventId);
		List<ParticipantsViewDTO> participants = toCollectionModel(event.getOrders());
		return participants;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> findAllReport(@PathVariable Integer eventId) {
		
		byte[] bytes = eventReportService.findAllEventParticipants(eventId);
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=event.pdf");
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(header)
				.body(bytes);
	}
	
	public static ParticipantsViewDTO toModel(Order order) {
		ParticipantsViewDTO participantsViewDTO = new ParticipantsViewDTO();
		participantsViewDTO.setPrice(order.getValueTotal());
		participantsViewDTO.setTicketsId(order.getPayment().getId());
		participantsViewDTO.setStatus(order.getPayment().getStatus());
		participantsViewDTO.setNameUser(order.getUser().getName());
		participantsViewDTO.setEmailUser(order.getUser().getEmail());
		participantsViewDTO.setPhoneUser(order.getUser().getPhone());
		return participantsViewDTO;
	}

	public static List<ParticipantsViewDTO> toCollectionModel(List<Order> orders) {
		return orders.stream().map(order -> toModel(order)).collect(Collectors.toList());
	}
}
