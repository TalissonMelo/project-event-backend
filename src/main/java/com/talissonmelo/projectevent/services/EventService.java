package com.talissonmelo.projectevent.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.repositories.EventRepository;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public List<Event> findAll() {
		List<Event> list = eventRepository.findAll();
		return list;
	}
	
	public Event findById(Integer id) {
		Optional<Event> event = eventRepository.findById(id);
		//return event.get();
		return event.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Event.class.getName()));
	}
}
