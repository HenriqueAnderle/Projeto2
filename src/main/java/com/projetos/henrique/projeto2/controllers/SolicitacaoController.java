package com.projetos.henrique.projeto2.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.henrique.projeto2.dtos.SolicitacaoDto;
import com.projetos.henrique.projeto2.models.Solicitacao;
import com.projetos.henrique.projeto2.services.SolicitacaoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/relatorio")
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
	
	public ResponseEntity<Page<Solicitacao>> getAllSolicitacoes(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(solicitacaoService.findAllSolicitacao(pageable));
	}
	
	//Fazer o findAllByUsuario depois
}
