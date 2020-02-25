package com.talissonmelo.projectevent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talissonmelo.projectevent.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
