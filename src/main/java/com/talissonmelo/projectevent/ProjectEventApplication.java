package com.talissonmelo.projectevent;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.talissonmelo.projectevent.domain.Category;
import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.repositories.CategoryRepository;
import com.talissonmelo.projectevent.repositories.EventRepository;

@SpringBootApplication
public class ProjectEventApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private EventRepository eventRepository;

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

		cat1.getEvents().addAll(Arrays.asList(ev1, ev2));
		cat2.getEvents().addAll(Arrays.asList(ev1));
		cat3.getEvents().addAll(Arrays.asList(ev1, ev2));

		ev1.getCategories().addAll(Arrays.asList(cat1, cat2, cat3));
		ev2.getCategories().addAll(Arrays.asList(cat1, cat3));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		eventRepository.saveAll(Arrays.asList(ev1, ev2));

	}

}
