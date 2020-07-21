package com.talissonmelo.projectevent.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.EventPhoto;
import com.talissonmelo.projectevent.dto.EventPhotoDTO;
import com.talissonmelo.projectevent.services.EventPhotoService;
import com.talissonmelo.projectevent.services.EventService;

@RestController
@RequestMapping(value = "events/{eventId}/photo")
public class EventoPhotoResource {
	
	@Autowired
	private EventService eventService;

	@Autowired
	private EventPhotoService eventPhotoService;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public EventPhoto updatePhoto(@PathVariable Integer eventId, @Valid EventPhotoDTO eventPhotoDTO) {
		
		Event event = eventService.findById(eventId);		
		MultipartFile file = eventPhotoDTO.getFile();
		
		EventPhoto eventPhoto = new EventPhoto();
		eventPhoto.setEvent(event);
		eventPhoto.setName(file.getOriginalFilename());
		eventPhoto.setDescription(eventPhotoDTO.getDescription());
		eventPhoto.setContentType(file.getContentType());
		eventPhoto.setSize(file.getSize());
		eventPhoto = eventPhotoService.save(eventPhoto);
		return eventPhoto;
	}
}
