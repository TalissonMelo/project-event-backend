package com.talissonmelo.projectevent.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.EventPhoto;
import com.talissonmelo.projectevent.repositories.EventRepository;

@Service
public class EventPhotoService {

	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventPhoto save(EventPhoto eventPhoto) {
		
		Optional<EventPhoto> photoEventExist = repository.findPhotoById(eventPhoto.getEvent().getId());
		
		if(photoEventExist.isPresent()) {
			repository.delete(photoEventExist.get());
		}
		
		return repository.save(eventPhoto);
	}
}
