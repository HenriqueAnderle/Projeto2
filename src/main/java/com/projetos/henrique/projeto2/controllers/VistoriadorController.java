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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.henrique.projeto2.dtos.VistoriadorDto;
import com.projetos.henrique.projeto2.models.Vistoriador;
import com.projetos.henrique.projeto2.services.VistoriadorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vistoriador")
public class VistoriadorController {

	final VistoriadorService vistoriadorService;
	
	public VistoriadorController(VistoriadorService vistoriadorService) {
		this.vistoriadorService = vistoriadorService;
	}
	
	@PostMapping
	public ResponseEntity<Object> inserirVistoriador(@RequestBody @Valid VistoriadorDto vistoriadorDto){
		if(vistoriadorService.existsByNome(vistoriadorDto.getNome())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Nome de Vistoriador Já em Uso");
		}
		
		Vistoriador vistoriador = new Vistoriador();
		BeanUtils.copyProperties(vistoriadorDto, vistoriador);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(vistoriadorService.inserirVistoriador(vistoriador));
	}
	
	@GetMapping
	public ResponseEntity<Page<Vistoriador>> getAllVistoriadores(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam UUID idRelatorio){
		return ResponseEntity.status(HttpStatus.OK).body(vistoriadorService.findAllByRelatorio(pageable, idRelatorio));
	}
	
	@GetMapping("/{idVistoriador}")
	public ResponseEntity<Object> getById(@PathVariable(value = "idVistoriador") UUID idVistoriador){
		Optional<Vistoriador> vistoriadorOptional = vistoriadorService.findById(idVistoriador);
		
		if(!vistoriadorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vistoriador Not Found");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(vistoriadorOptional.get());
	}
	
	public ResponseEntity<Object> deletarVistoriador(@PathVariable(value = "idVistoriador") UUID idVistoriador){
		Optional<Vistoriador> vistoriadorOptional = vistoriadorService.findById(idVistoriador);
		
		if(!vistoriadorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vistoriador Not Found");
		}
		
		vistoriadorService.deletarVistoriador(vistoriadorOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Vistoriador Deleted Successfully");
	}
}
