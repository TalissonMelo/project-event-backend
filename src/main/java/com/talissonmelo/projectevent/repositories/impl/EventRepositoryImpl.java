package com.talissonmelo.projectevent.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.talissonmelo.projectevent.domain.EventPhoto;
import com.talissonmelo.projectevent.repositories.EventRepositoryQueries;

@Repository
public class EventRepositoryImpl implements EventRepositoryQueries{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public EventPhoto save(EventPhoto photo) {
		return entityManager.merge(photo);
	}

	@Override
	public void delete(EventPhoto photo) {
		entityManager.remove(photo);		
	}

}
