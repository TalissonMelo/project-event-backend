package com.talissonmelo.projectevent.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.Photo;

@Repository
public interface EventRepository 
	extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event>, EventRepositoryQueries {

	@Query("from Photo p where p.event.id = :eventId")
	Optional<Photo> findPhotoById(Integer eventId);
}
