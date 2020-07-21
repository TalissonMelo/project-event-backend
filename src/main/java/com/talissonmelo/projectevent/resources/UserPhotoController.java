package com.talissonmelo.projectevent.resources;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.talissonmelo.projectevent.domain.User;
import com.talissonmelo.projectevent.repositories.UserRepository;
import com.talissonmelo.projectevent.services.UserService;

@RestController
@RequestMapping(value = "users/{userId}/photo")
public class UserPhotoController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repository;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void insertAvatar(@PathVariable Integer userId,@RequestParam MultipartFile file) {

		try {
			
			User user = service.findById(userId);
			
			String nameAvatar = UUID.randomUUID().toString() + "__" + file.getOriginalFilename();
			Path path = Paths.get("D://Pi//userPhotos", nameAvatar);
			file.transferTo(path);
			
			user.setPhoto(nameAvatar);
			repository.save(user);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
	}
}
