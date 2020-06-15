package com.talissonmelo.projectevent.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import com.talissonmelo.projectevent.services.UserService;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		List<UserDTO> users = userService.toDTO(list);
		return ResponseEntity.ok().body(users);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping
	public ResponseEntity<User> insert(@Valid @RequestBody UserDTO objDTO) {
		User obj = userService.fromDTO(objDTO);
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
		User obj = userService.fromDTO(objDTO);
		obj.setId(id);
		User entity = userService.update(id, obj);
		return ResponseEntity.ok().body(entity);
	}
	
}
