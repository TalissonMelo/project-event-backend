package com.talissonmelo.projectevent.repositories;

import com.talissonmelo.projectevent.domain.EventPhoto;

public interface EventRepositoryQueries {

	EventPhoto save(EventPhoto photo);
	
	void delete(EventPhoto photo);
}
