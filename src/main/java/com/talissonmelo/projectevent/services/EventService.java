package com.talissonmelo.projectevent.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.dto.EventDTO;
import com.talissonmelo.projectevent.repositories.EventRepository;
import com.talissonmelo.projectevent.services.exceptions.DataBaseException;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public List<Event> findAll(Event eventFilter) {
		
		Example<Event> example = Example.of(eventFilter, 
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		List<Event> list = eventRepository.findAll(example);
		return list;
	}

	public Event findById(Integer id) {
		Optional<Event> event = eventRepository.findById(id);
		// return event.get();
		return event.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Event.class.getName()));
	}

	public Event insert(Event obj) {
		obj = eventRepository.save(obj);
		return obj;
	}

	public void delete(Integer id) {
		try {
			eventRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Evento não pode ser deletado. " + e.getMessage());
		}
	}

	public Event update(Integer id, Event obj) {
		try {
			Event entity = eventRepository.getOne(id);
			updateData(entity, obj);
			return eventRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void updateData(Event entity, Event obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setInitialData(obj.getInitialData());
		entity.setFinalData(obj.getFinalData());
		entity.setPrice(obj.getPrice());
		entity.setType(obj.getType());

	}
	
	public Event fromDTO(EventDTO objDTO) {
		Event obj = new Event();
		obj.setId(objDTO.getId());
		obj.setName(objDTO.getName());
		obj.setDescription(objDTO.getDescription());
		obj.setInitialData(objDTO.getInitialData());
		obj.setFinalData(objDTO.getFinalData());
		obj.setPrice(objDTO.getPrice());
		obj.setType(objDTO.getType());
		
		return obj;
	}
}
