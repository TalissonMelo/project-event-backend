package com.talissonmelo.projectevent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talissonmelo.projectevent.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{

}
