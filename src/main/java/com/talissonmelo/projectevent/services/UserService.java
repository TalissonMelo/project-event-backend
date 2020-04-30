package com.talissonmelo.projectevent.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.User;
import com.talissonmelo.projectevent.dto.UserDTO;
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
		// return user.get();
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
			updateData(entity, obj);
			return userRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		entity.setPassword(obj.getPassword());
	}
	
	public User fromDTO(UserDTO objDto) {
		User user = new User();
		user.setId(objDto.getId());
		user.setName(objDto.getName());
		user.setEmail(objDto.getEmail());
		user.setUserType(objDto.getUserType());
		user.setCpfCnpj(objDto.getCpfCnpj());
		user.setPhone(objDto.getPhone());
		user.setPassword(objDto.getPassword());
		
		return user;
	}
	
	public List<UserDTO> toDTO(List<User> users ){
		List<UserDTO> userDTO = new ArrayList<>();
		
		for(User user: users) {
			UserDTO dto = new UserDTO();
			dto.setId(user.getId());
			dto.setName(user.getName());
			dto.setEmail(user.getEmail());
			dto.setPhone(user.getPhone());
			userDTO.add(dto);
		}
		
		return userDTO;
	}
}
