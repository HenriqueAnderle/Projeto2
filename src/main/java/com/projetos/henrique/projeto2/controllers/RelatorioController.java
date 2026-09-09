package com.projetos.henrique.projeto2.controllers;

import java.util.ArrayList;
import java.util.List;
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
import com.projetos.henrique.projeto2.dtos.RelatorioRequestDto;
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
	
	@PostMapping
	public ResponseEntity<Object> inserirRelatorio(@RequestBody @Valid RelatorioRequestDto relatorioRequest){
		
		Relatorio relatorio = relatorioService.inserirRelatorio(relatorioRequest.getRelatorio());
		
		List<NaoConformidade> naoConformidades = new ArrayList<NaoConformidade>();
		for(NaoConformidadeDto naoConformidadeDto : relatorioRequest.getNaoConformidades()) {

			NaoConformidade naoConformidade = new NaoConformidade();
			
			BeanUtils.copyProperties(naoConformidadeDto, naoConformidade);
			naoConformidade.setRelatorio(relatorio);
			
			naoConformidadeService.inserirNaoConformidade(naoConformidade);
			
			naoConformidades.add(naoConformidade);
		}
		
		List<Vistoriador> vistoriadores = new ArrayList<Vistoriador>();
		for(VistoriadorDto vistoriadorDto : relatorioRequest.getVistoriadores()) {
			
			Vistoriador vistoriador = new Vistoriador();
			
			BeanUtils.copyProperties(vistoriadorDto, vistoriador);
			vistoriador.setRelatorio(relatorio);
			
			vistoriadorService.inserirVistoriador(vistoriador);
			
			vistoriadores.add(vistoriador);
		}
								
		return ResponseEntity.status(HttpStatus.CREATED).body(relatorioRequest);
	}
	
	@GetMapping
	public ResponseEntity<Page<Relatorio>> getAllByUsuario(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable, 
			@RequestParam UUID idUsuario){
		return ResponseEntity.status(HttpStatus.OK).body(relatorioService.findAllByUsuario(pageable, idUsuario));
	}
	
	@GetMapping("/{idRelatorio}")
	public ResponseEntity<Object> getById(@PathVariable(value = "idRelatorio") UUID idRelatorio){
		
		RelatorioRequestDto relatorioRequestDto = new RelatorioRequestDto();
		
		Optional<Relatorio> relatorioOptional = relatorioService.findById(idRelatorio);
		if(!relatorioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relatorio Not Found");
		}
		relatorioRequestDto.setRelatorio(RelatorioDto.fromEntity(relatorioOptional.get()));
		
		//Talvez Não Precise Ser Optional Aqui Por Causa Que Talvez O Relatorio Não Tenha Nenhum Não Conformidade Mas Ainda Vou Pensar Se Isso Faz Sentido
		Optional<List<NaoConformidade>> naoConformidadesOptional = naoConformidadeService.findAllByRelatorio(idRelatorio); 
		if(!naoConformidadesOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Conformidades Not Found");
		}
		relatorioRequestDto.setNaoConformidades(NaoConformidadeDto.fromEntity(naoConformidadesOptional.get()));
		
		Optional<List<Vistoriador>> vistoriadoresOptional = vistoriadorService.findAllByRelatorio(idRelatorio);
		if(!vistoriadoresOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vistoriadores Not Found");
		}
		relatorioRequestDto.setVistoriadores(VistoriadorDto.fromEntity(vistoriadoresOptional.get()));
		
		return ResponseEntity.status(HttpStatus.OK).body(relatorioRequestDto);
	}
	
	@DeleteMapping("/{idRelatorio}")
	public ResponseEntity<Object> deletarObra(@PathVariable(value = "idRelatorio") UUID idRelatorio){
		
		Optional<Relatorio> relatorioOptional = relatorioService.findById(idRelatorio);
		
		if(!relatorioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relatorio Not Found");
		}
		
		Optional<List<NaoConformidade>> naoConformidadesOptional = naoConformidadeService.findAllByRelatorio(idRelatorio); 
		if(!naoConformidadesOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Conformidades Not Found");
		}
		
		Optional<List<Vistoriador>> vistoriadoresOptional = vistoriadorService.findAllByRelatorio(idRelatorio);
		if(!vistoriadoresOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vistoriadores Not Found");
		}
		
		relatorioService.deletarRelatorio(relatorioOptional.get());

		for(NaoConformidade naoConformidade : naoConformidadesOptional.get()) {
			naoConformidadeService.deletarNaoConformidade(naoConformidade);
		}
		
		for(Vistoriador vistoriador : vistoriadoresOptional.get()) {
			vistoriadorService.deletarVistoriador(vistoriador);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Relatorio Deleted Successfully");
	}
}
