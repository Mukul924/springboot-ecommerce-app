package com.ducat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ducat.security.CustomLoginSuccessHandler;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private CustomLoginSuccessHandler successHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http

        .csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(auth -> auth

            .requestMatchers(
                "/",
                "/signup",
                "/register",
                "/login",
                "/css/**",
                "/js/**"
            )

            .permitAll()

            .requestMatchers(
                "/sellerDashboard",
                "/addProduct",
                "/saveProduct",
                "/sellerOrders"
            )

            .hasAuthority("SELLER")

            .anyRequest()

            .authenticated()
        )

        .formLogin(form -> form

        	    .loginPage("/login")

        	    .loginProcessingUrl("/login")

        	    .successHandler(successHandler)

        	    .permitAll()
        	)

        .logout(logout -> logout

            .logoutSuccessUrl("/")
        );

        return http.build();
    }
}