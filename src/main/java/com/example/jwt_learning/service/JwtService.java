package com.example.jwt_learning.service;

import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
	
	
	private final SecretKey secretKey = Jwts.SIG.HS256.key().build();
	
	public String generateToken (String email) {
		return Jwts.builder()
				.subject(email)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(getSigningKey())
				.compact();
	}
	
	private SecretKey getSigningKey () {
		
		//byte[] keyBytes = Decoders.BASE64.decode(secret); ---> this will help us to read the existing secret string decodes it convert into the signin key 
		//return Keys.hmacShaKeyFor(keyBytes);
		return secretKey;
	}
	public String extractEmail (String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public boolean isTokenValid (String token , String email) {
		try {
			String tokenEmail = extractEmail(token);
			return tokenEmail.equals(tokenEmail);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	

}
