package com.talissonmelo.projectevent.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.EventPhoto;

@Repository
public interface EventRepository 
	extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event>, EventRepositoryQueries {

	@Query("from EventPhoto e where e.event.id = :eventId")
	Optional<EventPhoto> findPhotoById(Integer eventId);
}
