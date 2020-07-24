package com.talissonmelo.projectevent.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.Photo;
import com.talissonmelo.projectevent.dto.EventPhotoDTO;
import com.talissonmelo.projectevent.services.EventPhotoService;
import com.talissonmelo.projectevent.services.EventService;
import com.talissonmelo.projectevent.services.storage.PhotoStorageService;

@RestController
@RequestMapping(value = "events/{eventId}/photo")
public class EventPhotoResource {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private PhotoStorageService photoStorageService;

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
	public ResponseEntity<InputStreamResource> outputPhoto(@PathVariable Integer eventId,  @RequestHeader(name = "accept") String acceptHeader) {

		try {
			Photo photo = eventPhotoService.findByPhoto(eventId);
			
			MediaType mediaTypePhoto = MediaType.parseMediaType(photo.getContentType());
			List<MediaType> mediaTypesAccept = MediaType.parseMediaTypes(acceptHeader);

			mediaTypePhotoExistAccept(mediaTypePhoto, mediaTypesAccept);
			
			InputStream inputStream = photoStorageService.findByPhoto(photo.getName());
			return ResponseEntity.ok()
					.contentType(mediaTypePhoto)
					.body(new InputStreamResource(inputStream));
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Integer eventId) {
	   eventPhotoService.deletePhoto(eventId);
	}   
	
	private void mediaTypePhotoExistAccept(MediaType mediaTypePhoto, List<MediaType> mediaTypesAccept) throws HttpMediaTypeNotAcceptableException {
		boolean compatible = mediaTypesAccept
				.stream()
				.anyMatch(mediaTypesPhotoAccept -> mediaTypesPhotoAccept.isCompatibleWith(mediaTypePhoto));
		if(!compatible) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAccept);
		}
	}
}
