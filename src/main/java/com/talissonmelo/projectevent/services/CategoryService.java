package com.talissonmelo.projectevent.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Category;
import com.talissonmelo.projectevent.repositories.CategoryRepository;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}
	
	public Category findById(Integer id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		return categoryRepository.save(obj);
	}
	
	public void delete(Integer id) {
		categoryRepository.deleteById(id);
	}

	public Category update(Integer id, Category obj) {
		Category entity = categoryRepository.getOne(id);
		updateData(entity, obj);
		return categoryRepository.save(entity);
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());		
	}
}
