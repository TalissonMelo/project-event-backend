package com.talissonmelo.projectevent.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.City;
import com.talissonmelo.projectevent.repositories.CityRepository;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public City findById(Integer id) {
		Optional<City> obj = cityRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada : " + id));
	}
}
