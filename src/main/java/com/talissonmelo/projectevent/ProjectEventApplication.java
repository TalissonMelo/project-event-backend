package com.talissonmelo.projectevent;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.talissonmelo.projectevent.domain.Category;
import com.talissonmelo.projectevent.domain.City;
import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.domain.State;
import com.talissonmelo.projectevent.domain.User;
import com.talissonmelo.projectevent.domain.enums.UserType;
import com.talissonmelo.projectevent.repositories.CategoryRepository;
import com.talissonmelo.projectevent.repositories.CityRepository;
import com.talissonmelo.projectevent.repositories.EventRepository;
import com.talissonmelo.projectevent.repositories.StateRepository;
import com.talissonmelo.projectevent.repositories.UserRepository;

@SpringBootApplication
public class ProjectEventApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectEventApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Category cat1 = new Category(null, "Entretenimento");
		Category cat2 = new Category(null, "Palestra");
		Category cat3 = new Category(null, "Sociais");

		Event ev1 = new Event(null, "Apresentação Pi", "Apresentação dos Trabalhos de término de período.",
				sdf.parse("01/06/2020 19:15:00"), sdf.parse("01/06/2020 22:40:00"), 50.00);
		Event ev2 = new Event(null, "Bienal de ideias", "Apresentação dos Trabalhos", sdf.parse("01/08/2020 19:15:00"),
				sdf.parse("01/08/2020 22:40:00"), 80.00);

		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		eventRepository.saveAll(Arrays.asList(ev1, ev2));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City ct1 = new City(null, "Araguari", st1);
		City ct2 = new City(null, "Uberlândia", st1);
		City ct3 = new City(null, "São Paulo", st2);
		
		st1.getCities().addAll(Arrays.asList(ct1,ct2));
		st2.getCities().addAll(Arrays.asList(ct3));
		
		stateRepository.saveAll(Arrays.asList(st1,st2));
		cityRepository.saveAll(Arrays.asList(ct1,ct2,ct3));
		
		User user1 = new User(null, "Talisson Melo", "talisson.cursos@gmail.com", UserType.PHYSICS, "12345678", "3242-3414");

		User user2 = new User(null, "Tales", "tales.cursos@gmail.com", UserType.LEGAL, "12345008", "3242-3415");
		
		userRepository.saveAll(Arrays.asList(user1, user2));

		
		

	}

}
