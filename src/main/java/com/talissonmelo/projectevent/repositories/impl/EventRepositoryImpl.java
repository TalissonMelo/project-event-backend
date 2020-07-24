package com.talissonmelo.projectevent.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.talissonmelo.projectevent.domain.Photo;
import com.talissonmelo.projectevent.repositories.EventRepositoryQueries;

@Repository
public class EventRepositoryImpl implements EventRepositoryQueries{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public Photo save(Photo photo) {
		return entityManager.merge(photo);
	}

	@Override
	public void delete(Photo photo) {
		entityManager.remove(photo);		
	}

}
