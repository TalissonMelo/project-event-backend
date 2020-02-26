package com.talissonmelo.projectevent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talissonmelo.projectevent.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
