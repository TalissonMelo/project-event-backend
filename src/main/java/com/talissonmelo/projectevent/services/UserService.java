package com.talissonmelo.projectevent.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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

	@Autowired
	private EntityManager entityManager;

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

		entityManager.detach(obj);
		Optional<User> user = userRepository.findByEmail(obj.getEmail());

		if (user.isPresent() && !user.get().equals(obj)) {
			throw new ObjectNotFoundException("Já existe um usuário cadastrado com este email.");
		}

		return userRepository.save(obj);
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

	public User update(Integer id, User obj) {
		try {
			User entity = userRepository.getOne(id);
			BeanUtils.copyProperties(obj, entity, "id", "password");
			return userRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	public User authenticate(String email, String password) {
		Optional<User> user = userRepository.findByEmail(email);

		if (!user.isPresent()) {
			throw new ObjectNotFoundException("Usuário não encontrado!.");
		}

		if (!user.get().getPassword().equals(password)) {
			throw new ObjectNotFoundException("Senha inválida!.");
		}
		return user.get();
	}

	@Transactional
	public void updatePassword(Integer id, String password, String newPassword) {
		User user = findById(id);

		if (!user.getPassword().equals(password)) {
			throw new ObjectNotFoundException("Senha atual informada não coincide com a senha do usuário.");
		}

		user.setPassword(newPassword);
	}
}
