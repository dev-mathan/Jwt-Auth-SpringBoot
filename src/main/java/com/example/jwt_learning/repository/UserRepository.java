package com.example.jwt_learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwt_learning.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	 boolean existsByEmail(String email);
	 
	 Optional<User> findByEmail(String email);

}
