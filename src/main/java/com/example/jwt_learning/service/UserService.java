package com.example.jwt_learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt_learning.Dto.LoginRequest;
import com.example.jwt_learning.entity.User;
import com.example.jwt_learning.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	public User registerUser (User user) {
		 // Hash the plain-text password
        //String hashedPassword = passwordEncoder.encode(user.getPassword());

        // Replace the plain-text password with the hash
        //user.setPassword(hashedPassword);

        // Save the user in MySQL
        //return userRepository.save(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		    return userRepository.save(user);
	}
	
	public boolean validateUser(LoginRequest request) {
		
		User user = userRepository.findByEmail(request.getEmail()).orElseThrow(null);
		
		if (user == null) {
	        return false;
	    }
		 return passwordEncoder.matches(request.getPassword(),user.getPassword());
	}
	
	public String login (String email, String password) {
		
		// 1. Find the user by email
		User user =userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User Not Found"));
		
		// 2. Check the raw password against the stored hash
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}
		
		// 3. Generate a token after successful login
        return jwtService.generateToken(email);
	}

}
