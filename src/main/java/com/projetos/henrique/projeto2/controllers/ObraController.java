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

import com.projetos.henrique.projeto2.dtos.ObraDto;
import com.projetos.henrique.projeto2.models.Obra;
import com.projetos.henrique.projeto2.services.ObraService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/obra")
public class ObraController {

	final ObraService obraService;
	
	public ObraController(ObraService obraService) {
		this.obraService = obraService;
	}
	
	@PostMapping
	public ResponseEntity<Object> inserirObra(@RequestBody @Valid ObraDto obraDto){
		if(obraService.existsByNome(obraDto.getNome())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Nome de Obra Já em Uso");
		}
		
		Obra obra = new Obra();
		BeanUtils.copyProperties(obraDto, obra);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(obraService.inserirObra(obra));
	}
	
	@GetMapping
	public ResponseEntity<Page<Obra>> getAllObras(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(obraService.findAllObras(pageable));
	}
	
}
