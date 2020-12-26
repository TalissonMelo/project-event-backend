package com.talissonmelo.projectevent.resources;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.talissonmelo.projectevent.domain.User;
import com.talissonmelo.projectevent.repositories.UserRepository;
import com.talissonmelo.projectevent.services.UserService;
import com.talissonmelo.projectevent.services.exceptions.StorageException;
import com.talissonmelo.projectevent.services.storage.PhotoStorageService;

@RestController
@RequestMapping(value = "users/{userId}/photo")
public class UserPhotoController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PhotoStorageService photoStorageService;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void insertAvatar(@PathVariable Integer userId,@RequestParam MultipartFile file) {

		try {
			
			User user = service.findById(userId);
			
			String nameAvatar = UUID.randomUUID().toString() + "__" + file.getOriginalFilename();
			Path path = Paths.get("D://Pi//userPhotos", nameAvatar);
			file.transferTo(path);
			
			user.setPhoto(nameAvatar);
			user.setPhotoContext(file.getContentType());
			repository.save(user);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
	}
	
	@GetMapping
	public ResponseEntity<InputStreamResource> outputPhoto(@PathVariable Integer userId,  
			@RequestHeader(name = "accept") String acceptHeader) {

		try {
			User user = service.findById(userId);
			
			MediaType mediaTypePhoto = MediaType.parseMediaType(user.getPhotoContext());
			List<MediaType> mediaTypesAccept = MediaType.parseMediaTypes(acceptHeader);

			photoStorageService.mediaTypePhotoExistAccept(mediaTypePhoto, mediaTypesAccept);
			
			InputStream inputStream = photoStorageService.findByPhotoUser(user.getPhoto());
			return ResponseEntity.ok()
					.contentType(mediaTypePhoto)
					.body(new InputStreamResource(inputStream));
			
		} catch (Exception e) {
			throw new StorageException(e.getMessage(), e.getCause());
		}
	}	
}
