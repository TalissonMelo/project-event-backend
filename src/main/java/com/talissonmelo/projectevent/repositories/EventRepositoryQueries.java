package com.talissonmelo.projectevent.repositories;

import com.talissonmelo.projectevent.domain.Photo;

public interface EventRepositoryQueries {

	Photo save(Photo photo);
	
	void delete(Photo photo);
}
