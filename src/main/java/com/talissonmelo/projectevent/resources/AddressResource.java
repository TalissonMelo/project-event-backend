package com.talissonmelo.projectevent.resources;

import java.util.List;

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

import com.talissonmelo.projectevent.domain.Address;
import com.talissonmelo.projectevent.services.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressResource {

	@Autowired
	private AddressService addressService;

	@GetMapping
	public ResponseEntity<List<Address>> findAll() {
		List<Address> list = addressService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Address> findById(@PathVariable Integer id) {
		Address obj = addressService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Address> insert(@RequestBody Address obj) {
		obj = addressService.insert(obj);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		addressService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Address> update(@PathVariable Integer id, @RequestBody Address obj) {
		obj = addressService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
