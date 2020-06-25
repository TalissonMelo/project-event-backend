package com.talissonmelo.projectevent.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.City;
import com.talissonmelo.projectevent.domain.State;
import com.talissonmelo.projectevent.repositories.CityRepository;
import com.talissonmelo.projectevent.repositories.StateRepository;

@Service
public class DBService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;


	public void instantiateTestDataBase() throws ParseException {
		

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "Goiás");

		City ct1 = new City(null, "Araguari", st1);
		City ct2 = new City(null, "Itumbiara", st2);

		st1.getCities().addAll(Arrays.asList(ct1));
		st2.getCities().addAll(Arrays.asList(ct2));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(ct1, ct2));

	}
}
