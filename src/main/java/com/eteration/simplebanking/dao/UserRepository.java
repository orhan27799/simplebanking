package com.eteration.simplebanking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eteration.simplebanking.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	
	 Users findByUsername(String username);

}
