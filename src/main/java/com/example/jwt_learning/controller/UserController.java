package com.example.jwt_learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt_learning.Dto.LoginRequest;
import com.example.jwt_learning.entity.User;
import com.example.jwt_learning.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<User> registerUser (@RequestBody User user) {
	
		return new  ResponseEntity<User>(userService.registerUser(user),HttpStatus.CREATED);
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody LoginRequest request) {
//
//	    if (userService.validateUser(request)) {
//	        return ResponseEntity.ok("User is valid");
//	    }
//
//	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest request) {

	    String token = userService.login(request.getEmail(),request.getPassword());

	    return ResponseEntity.ok(token);
	}

}
