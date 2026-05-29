package com.ducat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ducat.entity.User;
import com.ducat.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User registerUser(User user) {

        return repo.save(user);
    }


	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}
    

}