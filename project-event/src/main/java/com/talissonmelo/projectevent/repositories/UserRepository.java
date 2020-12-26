package com.talissonmelo.projectevent.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talissonmelo.projectevent.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
