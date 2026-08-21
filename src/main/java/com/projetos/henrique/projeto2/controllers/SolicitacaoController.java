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

import com.projetos.henrique.projeto2.dtos.SolicitacaoDto;
import com.projetos.henrique.projeto2.models.Solicitacao;
import com.projetos.henrique.projeto2.services.SolicitacaoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/solicitacao")
public class SolicitacaoController {

	final SolicitacaoService solicitacaoService;
	
	public SolicitacaoController(SolicitacaoService solicitacaoService) {
		this.solicitacaoService = solicitacaoService;
	}
	
	@PostMapping
	public ResponseEntity<Object> inserirSolicitacao(@RequestBody @Valid SolicitacaoDto solicitacaoDto){
		
		Solicitacao solicitacao = new Solicitacao();
		BeanUtils.copyProperties(solicitacaoDto, solicitacao);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoService.inserirSolicitacao(solicitacao));
	}
	
	@GetMapping
	public ResponseEntity<Page<Solicitacao>> getAllByUsuario(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam UUID idUsuario){
		return ResponseEntity.status(HttpStatus.OK).body(solicitacaoService.findAllByUsuario(pageable, idUsuario));
	}
	
	@GetMapping("/{idSolicitacao}")
	public ResponseEntity<Object> getById(@PathVariable(value = "idSolicitacao") UUID idSolicitacao){
		Optional<Solicitacao> solicitacaoOptional = solicitacaoService.findById(idSolicitacao);
		
		if(!solicitacaoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitacao Not Found");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(solicitacaoOptional.get());
	}
	
	@DeleteMapping("/{idSolicitacao}")
	public ResponseEntity<Object> deletarSolicitacao(@PathVariable(value = "idSolicitacao") UUID idSolicitacao){
		Optional<Solicitacao> solicitacaoOptional = solicitacaoService.findById(idSolicitacao);
		
		if(!solicitacaoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitacao Not Found");
		}
		
		solicitacaoService.deletarSolicitacao(solicitacaoOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Solicitacao Deleted Successfully");
	}
}
