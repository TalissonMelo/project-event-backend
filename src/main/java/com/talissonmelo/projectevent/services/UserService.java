package com.talissonmelo.projectevent.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.User;
import com.talissonmelo.projectevent.repositories.UserRepository;
import com.talissonmelo.projectevent.services.exceptions.DataBaseException;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		List<User> list = userRepository.findAll();
		return list;
	}

	public User findById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id : " + id + " Tipo : " + User.class.getName()));
	}

	public User insert(User obj) {
		validateEmail(obj.getEmail());
		return userRepository.save(obj);
	}
	
	public void validateEmail(String email) {
		boolean exists = userRepository.existsByEmail(email);
		if(exists) {
			throw new DataBaseException("Já existe um usuário cadastrado com este email!.");
		}
	}

	public void delete(Integer id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Usuário não pode ser deletado. " + e.getMessage());
		}
	}
	
	public User authenticate(String email, String password) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if(!user.isPresent()) {
			throw new ObjectNotFoundException("Usuário não encontrado!.");
		}
		
		if(!user.get().getPassword().equals(password)) {
			throw new ObjectNotFoundException("Senha inválida!.");
		}
		return user.get();
	}

	public User update(Integer id, User obj) {
		try {
			User entity = userRepository.getOne(id);
			BeanUtils.copyProperties(obj, entity, "id");
			return userRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}
}
