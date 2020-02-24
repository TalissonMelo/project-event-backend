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
	
	public Event insert(Event obj) {
		obj = eventRepository.save(obj);
		return obj;
	}
	
	public void delete(Integer id) {
		eventRepository.deleteById(id);
	}
	
	public Event update(Integer id, Event obj) {
		Event entity = eventRepository.getOne(id);
		updateData(entity, obj);
		return eventRepository.save(entity);
	}

	private void updateData(Event entity, Event obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setInitialData(obj.getInitialData());
		entity.setFinalData(obj.getFinalData());
		entity.setPrice(obj.getPrice());
		
	}
}
