package com.projetos.henrique.projeto2.filters;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projetos.henrique.projeto2.services.JwtService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter{
	
	final JwtService jwtService;
	
	public JwtFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			token = authHeader.substring(7);
		}
		
		if(token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			Claims claims = jwtService.verifySignatureAndExtractClaims(token);
			String role = claims.get("Role", String.class);
			
			List<SimpleGrantedAuthority> simpleGrantedAuthority = List.of(new SimpleGrantedAuthority("ROLE_"+role));
			
			if(!jwtService.isTokenExpired(token)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(claims.getSubject(), simpleGrantedAuthority);
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
