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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.henrique.projeto2.dtos.RelatorioDto;
import com.projetos.henrique.projeto2.models.Relatorio;
import com.projetos.henrique.projeto2.services.RelatorioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/relatorio")
public class RelatorioController {
	
	final RelatorioService relatorioService;
	
	public RelatorioController(RelatorioService relatorioService) {
		this.relatorioService = relatorioService;
	}
	
	@PostMapping
	public ResponseEntity<Object> inserirRelatorio(@RequestBody @Valid RelatorioDto relatorioDto){
		Relatorio relatorio = new Relatorio();
		BeanUtils.copyProperties(relatorioDto, relatorio);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(relatorioService.inserirRelatorio(relatorio));
	}
	
	@GetMapping
	public ResponseEntity<Page<Relatorio>> getAllByUsuario(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable, 
			@RequestParam UUID idUsuario){
		return ResponseEntity.status(HttpStatus.OK).body(relatorioService.findAllByUsuario(pageable, idUsuario));
	}
	
	@GetMapping("/{idRelatorio}")
	public ResponseEntity<Object> getById(@PathVariable(value = "idRelatorio") UUID idRelatorio){
		Optional<Relatorio> relatorioOptional = relatorioService.findById(idRelatorio);
		
		if(!relatorioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relatorio Not Found");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(relatorioOptional.get());
	}
	
	@DeleteMapping("/{idRelatorio}")
	public ResponseEntity<Object> deletarObra(@PathVariable(value = "idRelatorio") UUID idRelatorio){
		Optional<Relatorio> relatorioOptional = relatorioService.findById(idRelatorio);
		
		if(!relatorioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relatorio Not Found");
		}
		
		relatorioService.deletarRelatorio(relatorioOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Relatorio Deleted Successfully");
	}
}
