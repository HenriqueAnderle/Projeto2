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

import com.projetos.henrique.projeto2.dtos.ObraDto;
import com.projetos.henrique.projeto2.models.Obra;
import com.projetos.henrique.projeto2.services.ObraService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/home/obras")
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
	public ResponseEntity<Page<Obra>> getAllByUsuario(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam UUID idUsuario){
		return ResponseEntity.status(HttpStatus.OK).body(obraService.findAllByUsuario(pageable, idUsuario));
	}
	
	@PutMapping("/{idObra}/editar")
	public ResponseEntity<Object> editarObra(@PathVariable(value = "idObra") UUID idObra, @RequestBody @Valid ObraDto obraDto){
		Optional<Obra> obraOptional = obraService.findById(idObra);
		
		if(!obraOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra Not Found");
		}
		
		Obra obra = new Obra();
		BeanUtils.copyProperties(obraOptional, obra);
		
		obra.setIdObra(obraOptional.get().getIdObra());
		obra.setUsuario(obraOptional.get().getUsuario());
		
		return ResponseEntity.status(HttpStatus.OK).body(obraService.inserirObra(obra));
	}
	
	/*
	@GetMapping("/{idObra}")
	public ResponseEntity<Object> getById(@PathVariable(value = "idObra") UUID idObra){
		Optional<Obra> obraOptional = obraService.findById(idObra);
		
		if(!obraOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra Not Found");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(obraOptional.get());
	}
	*/
	
	@DeleteMapping("/{idObra}")
	public ResponseEntity<Object> deletarObra(@PathVariable(value = "idObra") UUID idObra){
		Optional<Obra> obraOptional = obraService.findById(idObra);
		
		if(!obraOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra Not Found");
		}
		
		obraService.deletarObra(obraOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Obra Deleted Successfully");
	}
	
}
