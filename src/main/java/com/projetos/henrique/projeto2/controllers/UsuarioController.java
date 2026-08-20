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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.henrique.projeto2.dtos.UsuarioDto;
import com.projetos.henrique.projeto2.models.Usuario;
import com.projetos.henrique.projeto2.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UsuarioController {

	final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Object> inserirUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
		if(usuarioService.existsByEmail(usuarioDto.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email Já em Uso");
		}
		
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.inserirUsuario(usuario));
	}
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> getAllUsuarios(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAllUsuario(pageable));
	}
	
	@DeleteMapping("/{idUsuario}")
	public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "idUsuario") UUID idUsuario){
		Optional<Usuario> usuarioOptional = usuarioService.findById(idUsuario);
		
		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario Not Found");
		}
		
		usuarioService.deletarUsuario(usuarioOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Usuario Deleted Successfully");
	}
	
}
