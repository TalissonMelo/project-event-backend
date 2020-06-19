package com.talissonmelo.projectevent.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.talissonmelo.projectevent.domain.User;
import com.talissonmelo.projectevent.dto.UserAuthenticateDTO;
import com.talissonmelo.projectevent.dto.UserDTO;
import com.talissonmelo.projectevent.dto.UserListDTO;
import com.talissonmelo.projectevent.services.UserService;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<UserListDTO>> findAll() {
		List<User> list = userService.findAll();
		List<UserListDTO> users = toCollectionModel(list);
		return ResponseEntity.ok().body(users);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping
	public ResponseEntity<User> insert(@Valid @RequestBody UserDTO objDTO) {
		User obj = toEntity(objDTO);
		userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody UserAuthenticateDTO objDTO) {
		try {
			User user = userService.authenticate(objDTO.getEmail(), objDTO.getPassword());
			return ResponseEntity.ok().body(user);
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@Valid @PathVariable Integer id, @RequestBody UserDTO objDTO) {
		User obj = toEntity(objDTO);
		User entity = userService.update(id, obj);
		return ResponseEntity.ok().body(entity);
	}

	private User toEntity(UserDTO objDTO) {
		return mapper.map(objDTO, User.class);
	}
	
	private UserListDTO toModelMapper(User user) {
		return mapper.map(user, UserListDTO.class);
	}
	
	private List<UserListDTO> toCollectionModel(List<User> users){
		return users.stream().map(user -> toModelMapper(user)).collect(Collectors.toList());
				
	}
}
