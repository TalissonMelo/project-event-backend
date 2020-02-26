package com.talissonmelo.projectevent.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Category;
import com.talissonmelo.projectevent.repositories.CategoryRepository;
import com.talissonmelo.projectevent.services.exceptions.DataBaseException;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
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
		try {
			categoryRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public Category update(Integer id, Category obj) {
		try {
		Category entity = categoryRepository.getOne(id);
		updateData(entity, obj);
		return categoryRepository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
	}
}
