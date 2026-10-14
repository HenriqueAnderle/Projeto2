package com.projetos.henrique.projeto2.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projetos.henrique.projeto2.filters.JwtFilter;
import com.projetos.henrique.projeto2.repositories.UsuarioRepository;
import com.projetos.henrique.projeto2.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	final UsuarioRepository usuarioRepository;
	final JwtFilter jwtFilter;
	
	public SecurityConfig(UsuarioRepository usuarioRepository, JwtFilter jwtFilter) {
		this.usuarioRepository = usuarioRepository;
		this.jwtFilter = jwtFilter;
	}
	
	@Bean
    public SecurityFilterChain basicAuth(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
        	.requestMatchers("/home").permitAll()
            .anyRequest().authenticated()
        ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
    	return new UsuarioService(usuarioRepository);
    }
    
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    	DaoAuthenticationProvider daoAuthenticationProvider =  new DaoAuthenticationProvider(userDetailsService);
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    	return new ProviderManager(daoAuthenticationProvider);
    }
    
    @Bean
    public PasswordEncoder passwordEnconder() {
    	return new BCryptPasswordEncoder();
    }
}
