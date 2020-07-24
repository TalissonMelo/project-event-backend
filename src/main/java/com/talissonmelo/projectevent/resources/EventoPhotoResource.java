package com.talissonmelo.projectevent.resources;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.Photo;
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
	public Photo updatePhoto(@PathVariable Integer eventId, @Valid EventPhotoDTO eventPhotoDTO) throws IOException {
		
		Event event = eventService.findById(eventId);		
		MultipartFile file = eventPhotoDTO.getFile();
		
		Photo eventPhoto = new Photo();
		eventPhoto.setEvent(event);
		eventPhoto.setName(file.getOriginalFilename());
		eventPhoto.setDescription(eventPhotoDTO.getDescription());
		eventPhoto.setContentType(file.getContentType());
		eventPhoto.setSize(file.getSize());
		eventPhoto = eventPhotoService.save(eventPhoto, file.getInputStream());
		return eventPhoto;
	}
	
	@GetMapping
	public Photo findByPhotoEvent(@PathVariable Integer eventId) {
		return eventPhotoService.findByPhoto(eventId);
	}
}
