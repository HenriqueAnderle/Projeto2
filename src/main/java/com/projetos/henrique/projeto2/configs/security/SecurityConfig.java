package com.projetos.henrique.projeto2.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.projetos.henrique.projeto2.repositories.UsuarioRepository;
import com.projetos.henrique.projeto2.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	final UsuarioRepository usuarioRepository;
	
	public SecurityConfig(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Bean
    public SecurityFilterChain basicAuth(HttpSecurity http) throws Exception {
		http
        .authorizeHttpRequests(auth -> auth
        	.requestMatchers("/usuario").permitAll()
            .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable());

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
