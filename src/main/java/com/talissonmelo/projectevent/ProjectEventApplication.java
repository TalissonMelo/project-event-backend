package com.talissonmelo.projectevent;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.talissonmelo.projectevent.domain.Category;
import com.talissonmelo.projectevent.repositories.CategoryRepository;

@SpringBootApplication
public class ProjectEventApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectEventApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Entretenimento");
		Category cat2 = new Category(null, "Corporativos");
		Category cat3 = new Category(null, "Congresso");
		Category cat4 = new Category(null, "Seminário");
		Category cat5 = new Category(null, "Palestra");
		Category cat6 = new Category(null, "Esportistas");
		Category cat7 = new Category(null, "Sociais");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

	}

}
