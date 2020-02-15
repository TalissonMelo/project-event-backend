package com.talissonmelo.projectevent.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Category;
import com.talissonmelo.projectevent.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}
	
	public Category findById(Integer id) {
		Optional<Category> categoria = categoryRepository.findById(id);
		return categoria.get();
	}

}
