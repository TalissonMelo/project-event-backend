package com.talissonmelo.projectevent.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talissonmelo.projectevent.domain.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Category> findAll(){
		
		Category cat1 = new Category(1, "Entretenimento");
		Category cat2 = new Category(2, "Corporativos");
		
		List<Category> categories = new ArrayList<>();
		categories.add(cat1);
		categories.add(cat2);
		
		return categories;
		
	}
	
}
