package com.ducat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducat.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    User findByEmailAndRole(String email, String role);

	User findByEmail(String email);

}