package com.talissonmelo.projectevent.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.services.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventResource {

	@Autowired
	private EventService eventService;

	@GetMapping
	public ResponseEntity<List<Event>> findAll() {
		List<Event> list = eventService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Event> findById(@PathVariable Integer id) {
		Event event = eventService.findById(id);
		return ResponseEntity.ok().body(event);
	}
	
	@DeleteMapping(value  = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		eventService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
