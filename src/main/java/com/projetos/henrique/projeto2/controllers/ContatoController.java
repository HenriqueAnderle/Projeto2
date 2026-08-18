package com.projetos.henrique.projeto2.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.henrique.projeto2.dtos.ContatoDto;
import com.projetos.henrique.projeto2.models.Contato;
import com.projetos.henrique.projeto2.services.ContatoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/contato")
public class ContatoController {

	final ContatoService contatoService;
	
	public ContatoController(ContatoService contatoService) {
		this.contatoService = contatoService;
	}
	
	@PostMapping
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
	public ResponseEntity<Page<Contato>> getAllContatos(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(contatoService.findAllContatos(pageable));
	}
	
}
