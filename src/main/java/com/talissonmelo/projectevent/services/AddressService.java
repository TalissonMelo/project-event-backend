package com.talissonmelo.projectevent.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talissonmelo.projectevent.domain.Address;
import com.talissonmelo.projectevent.repositories.AddressRepository;

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
		return obj.get();
	}

	public Address insert(Address obj) {
		obj = addressRepository.save(obj);
		return obj;
	}

	public void delete(Integer id) {
		addressRepository.deleteById(id);
	}

	public Address update(Integer id, Address obj) {
		Address entity = addressRepository.getOne(id);
		updateData(entity, obj);
		return addressRepository.save(entity);
	}

	private void updateData(Address entity, Address obj) {

		entity.setNeighborhooh(obj.getNeighborhooh());
		entity.setStreet(obj.getStreet());
		entity.setNumber(obj.getNumber());

	}
}
