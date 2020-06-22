package com.talissonmelo.projectevent.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Address;
import com.talissonmelo.projectevent.domain.City;
import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.User;
import com.talissonmelo.projectevent.dto.EventDTO;
import com.talissonmelo.projectevent.dto.EventNewDTO;
import com.talissonmelo.projectevent.dto.EventViewDTO;
import com.talissonmelo.projectevent.repositories.AddressRepository;
import com.talissonmelo.projectevent.repositories.EventRepository;
import com.talissonmelo.projectevent.services.exceptions.DataBaseException;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CityService cityService;

	public List<Event> findAll(Event eventFilter) {

		Example<Event> example = Example.of(eventFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));

		List<Event> list = eventRepository.findAll(example);
		return list;
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Event findById(Integer id) {
		Optional<Event> event = eventRepository.findById(id);
		// return event.get();
		return event.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Event.class.getName()));
	}

	@Transactional
	public Event insert(Event obj) {
		addressRepository.save(obj.getAddress());
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

	public Event fromDTO(EventNewDTO objDTO) {

		User user = userService.findById(objDTO.getUser());

		City city = cityService.findById(objDTO.getCidadeId());

		Address address = new Address();
		address.setId(null);
		address.setNeighborhooh(objDTO.getNeighborhooh());
		address.setComplement(objDTO.getComplement());
		address.setStreet(objDTO.getStreet());
		address.setNumber(objDTO.getNumber());
		address.setCep(objDTO.getCep());
		address.setCity(city);

		Event obj = new Event();
		obj.setId(null);
		obj.setName(objDTO.getName());
		obj.setDescription(objDTO.getDescription());
		obj.setInitialData(objDTO.getInitialData());
		obj.setFinalData(objDTO.getFinalData());
		obj.setPrice(objDTO.getPrice());
		obj.setType(objDTO.getType());
		obj.setAddress(address);

		if (objDTO.getParticipants() != null) {

			String[] fields = objDTO.getParticipants().split(" ");
			for (String x : fields) {
				obj.getParticipants().add(x);
			}
		}

		obj.setUser(user);
		user.getEvents().addAll(Arrays.asList(obj));

		return obj;
	}

	public EventViewDTO toView(Event event) {
		EventViewDTO dto = new EventViewDTO();

		dto.setId(event.getId());
		dto.setName(event.getName());
		dto.setDescription(event.getDescription());
		dto.setInitialData(event.getInitialData());
		dto.setFinalData(event.getFinalData());
		dto.setPrice(event.getPrice());
		dto.setType(event.getType().getCod());
		dto.setNeighborhooh(event.getAddress().getNeighborhooh());
		dto.setComplement(event.getAddress().getComplement());
		dto.setStreet(event.getAddress().getStreet());
		dto.setNumber(event.getAddress().getNumber());
		dto.setCep(event.getAddress().getCep());
		dto.setCidadeId(event.getAddress().getCity().getId());

		return dto;
	}
}
