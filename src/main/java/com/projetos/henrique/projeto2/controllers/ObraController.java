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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.henrique.projeto2.dtos.ObraDto;
import com.projetos.henrique.projeto2.models.Obra;
import com.projetos.henrique.projeto2.models.Usuario;
import com.projetos.henrique.projeto2.services.ObraService;
import com.projetos.henrique.projeto2.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/home/obras")
public class ObraController {

	final ObraService obraService;
	final UsuarioService usuarioService;
	
	public ObraController(ObraService obraService, UsuarioService usuarioService) {
		this.obraService = obraService;
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Object> inserirObra(@RequestBody @Valid ObraDto obraDto, Authentication authentication){
		if(obraService.existsByNome(obraDto.getNome())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Nome de Obra Já em Uso");
		}
		
		Obra obra = new Obra();
		BeanUtils.copyProperties(obraDto, obra);
		
		Optional<Usuario> usuarioOptional = usuarioService.findByEmail(authentication.getName());
		obra.setUsuario(usuarioOptional.get());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(obraService.inserirObra(obra));
	}
	
	@GetMapping
	public ResponseEntity<Page<Obra>> getAllByUsuario(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable,
			Authentication authentication){
		Optional<Usuario> usuarioOptional = usuarioService.findByEmail(authentication.getName());
		
		return ResponseEntity.status(HttpStatus.OK).body(obraService.findAllByUsuario(pageable, usuarioOptional.get().getIdUsuario()));
	}
	
	@PutMapping("/{idObra}/editar")
	public ResponseEntity<Object> editarObra(@PathVariable(value = "idObra") UUID idObra, @RequestBody @Valid ObraDto obraDto, Authentication authentication){
		
		Optional<Usuario> usuarioOptional = usuarioService.findByEmail(authentication.getName());
		
		Optional<Obra> obraOptional = obraService.findByIdObraAndUsuario_IdUsuario(idObra, usuarioOptional.get().getIdUsuario());
				
		if(!obraOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra Not Found");
		}
		
		Obra obra = new Obra();
		BeanUtils.copyProperties(obraDto, obra);
		
		obra.setIdObra(obraOptional.get().getIdObra());
		obra.setUsuario(obraOptional.get().getUsuario());
		
		return ResponseEntity.status(HttpStatus.OK).body(obraService.inserirObra(obra));
	}
	
	@DeleteMapping("/{idObra}")
	public ResponseEntity<Object> deletarObra(@PathVariable(value = "idObra") UUID idObra, Authentication authentication){
		
		Optional<Usuario> usuarioOptional = usuarioService.findByEmail(authentication.getName());
		
		Optional<Obra> obraOptional = obraService.findByIdObraAndUsuario_IdUsuario(idObra, usuarioOptional.get().getIdUsuario());
		
		if(!obraOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra Not Found");
		}
		
		obraService.deletarObra(obraOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Obra Deleted Successfully");
	}
	
}
