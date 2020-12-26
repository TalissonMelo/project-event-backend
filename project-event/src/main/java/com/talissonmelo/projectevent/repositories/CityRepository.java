package com.talissonmelo.projectevent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talissonmelo.projectevent.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
