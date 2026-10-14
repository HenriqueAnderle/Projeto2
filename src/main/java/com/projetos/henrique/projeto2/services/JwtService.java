package com.projetos.henrique.projeto2.services;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET = "";
	
	public String generateToken(String email, String role) {
		
		HashMap<String, Object> claims = new HashMap<String, Object>();
			
		claims.put("Role", role);
		
		return Jwts.builder()
				.subject(email)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000*60*30))
				.claims(claims)
				.signWith(getSignedKey())				
				.compact();
	}

	private SecretKey getSignedKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	}
	
	public Claims verifySignatureAndExtractClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSignedKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public String extractEmail(String token) {
		return verifySignatureAndExtractClaims(token).getSubject();
	}
	
	public Date getExpiration(String token) {
		return verifySignatureAndExtractClaims(token).getExpiration();
	}
	
	public boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}
}
