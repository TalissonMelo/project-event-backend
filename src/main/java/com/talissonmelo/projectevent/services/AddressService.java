package com.talissonmelo.projectevent.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Address;
import com.talissonmelo.projectevent.repositories.AddressRepository;
import com.talissonmelo.projectevent.services.exceptions.DataBaseException;
import com.talissonmelo.projectevent.services.exceptions.ObjectNotFoundException;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	public List<Address> findAll() {
		List<Address> list = addressRepository.findAll();
		return list;
	}

	public Address findById(Integer id) {
		Optional<Address> obj = addressRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}

	public Address insert(Address obj) {
		obj = addressRepository.save(obj);
		return obj;
	}

	public void delete(Integer id) {
		try {
			addressRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public Address update(Integer id, Address obj) {
		try {
		Address entity = addressRepository.getOne(id);
		updateData(entity, obj);
		return addressRepository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(id);
		}
	}

	private void updateData(Address entity, Address obj) {

		entity.setNeighborhooh(obj.getNeighborhooh());
		entity.setStreet(obj.getStreet());
		entity.setNumber(obj.getNumber());

	}
}
