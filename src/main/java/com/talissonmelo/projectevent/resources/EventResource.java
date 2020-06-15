package com.talissonmelo.projectevent.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.dto.EventDTO;
import com.talissonmelo.projectevent.dto.EventNewDTO;
import com.talissonmelo.projectevent.dto.EventViewDTO;
import com.talissonmelo.projectevent.dto.UserEventDTO;
import com.talissonmelo.projectevent.services.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventResource {

	@Autowired
	private EventService eventService;
	
	@GetMapping
	public ResponseEntity<List<Event>> findAll(
			@RequestParam(value = "name" , required = false) String name) {
				
		Event eventFilter = new Event();
		eventFilter.setName(name);
		
		List<Event> list = eventService.findAll(eventFilter);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EventViewDTO> findById(@PathVariable Integer id) {
		Event event = eventService.findById(id);
		EventViewDTO dto = eventService.toView(event);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value  = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		eventService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Event> insert(@Valid @RequestBody EventNewDTO objDTO) {
		Event obj = eventService.fromDTO(objDTO);
		eventService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Event> update(@Valid @PathVariable Integer id, @RequestBody EventDTO objDTO){
		Event obj = eventService.fromDTO(objDTO);
		obj.setId(id);
		Event entity = eventService.update(id, obj);
		return ResponseEntity.ok().body(entity);
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<UserEventDTO>> findUserEvent(){
		List<Event> list = eventService.findAll();
		List<UserEventDTO> listDTO = eventService.toUserEventDTO(list);
		return ResponseEntity.ok().body(listDTO);
	}

}
