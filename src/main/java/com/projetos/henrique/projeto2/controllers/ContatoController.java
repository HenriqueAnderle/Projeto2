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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.henrique.projeto2.dtos.ContatoDto;
import com.projetos.henrique.projeto2.models.Contato;
import com.projetos.henrique.projeto2.services.ContatoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/home/contatos")
public class ContatoController {

	final ContatoService contatoService;
	
	public ContatoController(ContatoService contatoService) {
		this.contatoService = contatoService;
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<Object> inserirContato(@RequestBody @Valid ContatoDto contatoDto){
		if(contatoService.existsByNome(contatoDto.getNome())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Nome de Contato Já em Uso");
		}
		if(contatoService.existsByEmail(contatoDto.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email de Contato Já em Uso");
		}
		
		Contato contato = new Contato();
		BeanUtils.copyProperties(contatoDto, contato);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.inserirContato(contato));
	}
	
	@GetMapping
	public ResponseEntity<Page<Contato>> getAllContatos(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam UUID idUsuario){		
		return ResponseEntity.status(HttpStatus.OK).body(contatoService.findAllByUsuario(pageable, idUsuario));
	}
	
	@PutMapping("/{idContato}/editar")
	public ResponseEntity<Object> editarContato(@PathVariable(value = "idContato") UUID idContato, @RequestBody @Valid ContatoDto contatoDto){
		Optional<Contato> contatoOptional = contatoService.findById(idContato);
		
		if(!contatoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato Not Found");
		}
		
		Contato contato = new Contato();
		BeanUtils.copyProperties(contatoDto, contato);
		
		contato.setIdContato(contatoOptional.get().getIdContato());
		contato.setUsuario(contatoOptional.get().getUsuario());
		
		return ResponseEntity.status(HttpStatus.OK).body(contatoService.inserirContato(contato));
		
	}
	
	/*
	@GetMapping("/{idContato}")
	public ResponseEntity<Object> getByUsuario(@PathVariable(value = "idContato") UUID idContato){
		Optional<Contato> contatoOptional = contatoService.findById(idContato);
		if(!contatoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato Not Found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(contatoOptional.get());
		
	}
	*/
	
	@DeleteMapping("/{idContato}")
	public ResponseEntity<Object> deletarContato(@PathVariable(value = "idContato") UUID idContato){
		Optional<Contato> contatoOptional = contatoService.findById(idContato);
		
		if(!contatoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato Not Found");
		}
		
		contatoService.deletarContato(contatoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Contato Deleted Successfully");
	}
	
}
