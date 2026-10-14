package com.projetos.henrique.projeto2.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.henrique.projeto2.dtos.LoginRequest;
import com.projetos.henrique.projeto2.dtos.UsuarioDto;

import com.projetos.henrique.projeto2.models.Usuario;
import com.projetos.henrique.projeto2.services.JwtService;
import com.projetos.henrique.projeto2.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/home")
public class UsuarioController {

	final UsuarioService usuarioService;
	final PasswordEncoder passwordEncoder;
	final AuthenticationManager authenticationManager;
	final JwtService jwtService;
	
	public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
			JwtService jwtService) {
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> inserirUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
		if(usuarioService.existsByEmail(usuarioDto.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email Já em Uso");
		}
		
		usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
		
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuario);
		
		usuario.setRole("ROLE_USER");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.inserirUsuario(usuario));
	}
	
	@PostMapping("/login")
	public String authenticate(@RequestBody LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));
		
		if(authenticate.isAuthenticated()) {
						
			String roleString = authenticate
				.getAuthorities()
				.iterator()
				.next()
				.getAuthority()
				.replace("ROLE_", "");
		
			return jwtService.generateToken(loginRequest.getEmail(), roleString);
		}
		return null;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Page<Usuario>> getAll(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll(pageable));
	}
	
	@GetMapping("/{idUsuario}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getById(@PathVariable(value = "idUsuario") UUID idUsuario){
		Optional<Usuario> usuarioOptional = usuarioService.findById(idUsuario);
		
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario Not Found");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
	}
	
	@DeleteMapping("/{idUsuario}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "idUsuario") UUID idUsuario){
		Optional<Usuario> usuarioOptional = usuarioService.findById(idUsuario);
		
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario Not Found");
		}
		
		usuarioService.deletarUsuario(usuarioOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Usuario Deleted Successfully");
	}
	
}
