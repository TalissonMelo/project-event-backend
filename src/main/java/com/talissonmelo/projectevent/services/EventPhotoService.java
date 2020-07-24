package com.talissonmelo.projectevent.services;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Photo;
import com.talissonmelo.projectevent.repositories.EventRepository;
import com.talissonmelo.projectevent.services.storage.PhotoStorageService;
import com.talissonmelo.projectevent.services.storage.PhotoStorageService.NewPhoto;

@Service
public class EventPhotoService {

	@Autowired
	private EventRepository repository;
	
	@Autowired
	private PhotoStorageService photoStorageService;
	
	@Transactional
	public Photo save(Photo eventPhoto, InputStream photoData) {
		
		Optional<Photo> photoEventExist = repository.findPhotoById(eventPhoto.getEvent().getId());
		String newNameFile = photoStorageService.newNameFile(eventPhoto.getName());
		String nameFileExist = null;
		
		if(photoEventExist.isPresent()) {
			nameFileExist = photoEventExist.get().getName();
			repository.delete(photoEventExist.get());
		}
		
		eventPhoto.setName(newNameFile);
		eventPhoto = repository.save(eventPhoto);
		repository.flush();
		
		NewPhoto newPhoto = new NewPhoto();
		newPhoto.setNameFile(eventPhoto.getName());
		newPhoto.setStream(photoData);
		
		photoStorageService.replacePhoto(nameFileExist , newPhoto);
		
		return eventPhoto;
	}
}
