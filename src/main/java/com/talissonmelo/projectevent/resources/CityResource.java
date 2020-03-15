package com.talissonmelo.projectevent.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talissonmelo.projectevent.domain.City;
import com.talissonmelo.projectevent.services.CityService;

@RestController
@RequestMapping("/cities")
public class CityResource {
	
	@Autowired
	private CityService cityService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<City> findById(@PathVariable Integer id){
		City city = cityService.findById(id);
		return ResponseEntity.ok().body(city);
	}
}
