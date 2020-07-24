package com.talissonmelo.projectevent.resources;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talissonmelo.projectevent.dto.photo.EventPhotoDTO;

@RestController
@RequestMapping(value = "/events/{eventId}/photo")
public class EventPhotoController {

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void insertPhoto(@PathVariable Integer eventId,@Valid EventPhotoDTO file) {

		try {
			String nameFile = UUID.randomUUID().toString() + "__" + file.getFile().getOriginalFilename();
			Path path = Paths.get("D://event", nameFile);
			file.getFile().transferTo(path);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}

	}

}
