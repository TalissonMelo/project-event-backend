package com.talissonmelo.projectevent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talissonmelo.projectevent.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{
}
