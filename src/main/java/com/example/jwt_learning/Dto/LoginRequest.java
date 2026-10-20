package com.example.jwt_learning.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginRequest {
	
	private String email;
    private String password;

}
