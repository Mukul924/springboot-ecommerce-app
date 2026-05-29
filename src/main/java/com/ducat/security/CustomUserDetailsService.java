package com.ducat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.ducat.entity.User;
import com.ducat.repository.UserRepository;

@Service
public class CustomUserDetailsService
implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user =
                repo.findByEmail(email);

        if(user == null) {

            throw new UsernameNotFoundException(
                    "User Not Found");
        }

        return org.springframework.security.core.userdetails
                .User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }
}