package com.talissonmelo.projectevent.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.talissonmelo.projectevent.domain.Category;
import com.talissonmelo.projectevent.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService categotyService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findById(@PathVariable Integer id){
		Category obj = categotyService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = categotyService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
}
