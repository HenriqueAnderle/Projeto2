package com.projetos.henrique.projeto2.controllers;

import java.util.List;
import java.util.Map;
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

import com.projetos.henrique.projeto2.dtos.NaoConformidadeDto;
import com.projetos.henrique.projeto2.dtos.RelatorioDto;
import com.projetos.henrique.projeto2.dtos.VistoriadorDto;
import com.projetos.henrique.projeto2.models.NaoConformidade;
import com.projetos.henrique.projeto2.models.Relatorio;
import com.projetos.henrique.projeto2.models.Vistoriador;
import com.projetos.henrique.projeto2.services.NaoConformidadeService;
import com.projetos.henrique.projeto2.services.RelatorioService;
import com.projetos.henrique.projeto2.services.VistoriadorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/relatorio")
public class RelatorioController {
	
	final RelatorioService relatorioService;
	final NaoConformidadeService naoConformidadeService;
	final VistoriadorService vistoriadorService;
	
	public RelatorioController(RelatorioService relatorioService, NaoConformidadeService naoConformidadeService, VistoriadorService vistoriadorService) {
		this.relatorioService = relatorioService;
		this.naoConformidadeService = naoConformidadeService;
		this.vistoriadorService = vistoriadorService;
	}
	
	//TODO refazer o jeito que retorna o body
	@PostMapping
	public ResponseEntity<Object> inserirRelatorio(@RequestBody @Valid RelatorioDto relatorioDto, @RequestBody @Valid List<NaoConformidadeDto> naoConformidadesDto, 
			@RequestBody @Valid List<VistoriadorDto> vistoriadoresDto){
		Relatorio relatorio = new Relatorio();
		NaoConformidade naoConformidade = new NaoConformidade();
		Vistoriador vistoriador = new Vistoriador();
		
		BeanUtils.copyProperties(relatorioDto, relatorio);
		
		for(int i = 0; i < naoConformidadesDto.size(); i++) {
			BeanUtils.copyProperties(naoConformidadesDto.get(i), naoConformidade);
			naoConformidadeService.inserirNaoConformidade(naoConformidade);
		}
		
		for(int i = 0; i < vistoriadoresDto.size(); i++) {
			BeanUtils.copyProperties(vistoriadoresDto.get(i), vistoriador);
			vistoriadorService.inserirVistoriador(vistoriador);
		}
						
		return ResponseEntity.status(HttpStatus.CREATED).body(relatorioService.inserirRelatorio(relatorio));
	}
	
	@GetMapping
	public ResponseEntity<Page<Relatorio>> getAllByUsuario(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable, 
			@RequestParam UUID idUsuario){
		return ResponseEntity.status(HttpStatus.OK).body(relatorioService.findAllByUsuario(pageable, idUsuario));
	}
	
	//Ver como faz pra retornar mais de um objeto no body
	@GetMapping("/{idRelatorio}")
	public ResponseEntity<List<Object>> getById(@PathVariable(value = "idRelatorio") UUID idRelatorio){
		Optional<Relatorio> relatorioOptional = relatorioService.findById(idRelatorio);
		if(!relatorioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relatorio Not Found");
		}
		Optional<List<NaoConformidade>> naoConformidadesOptional = naoConformidadeService.findAllByRelatorio(idRelatorio); 
		if(!naoConformidadesOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Conformidades Not Found");
		}
		Optional<List<Vistoriador>> vistoriadorOptional = vistoriadorService.findAllByRelatorio(idRelatorio);
		if(!vistoriadorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vistoriadores Not Found");
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
