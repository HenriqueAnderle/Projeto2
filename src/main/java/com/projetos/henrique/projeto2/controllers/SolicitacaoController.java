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

import com.projetos.henrique.projeto2.dtos.MaterialServicoDto;
import com.projetos.henrique.projeto2.dtos.SolicitacaoRequestDto;
import com.projetos.henrique.projeto2.dtos.SolicitacaoResponseDto;
import com.projetos.henrique.projeto2.models.MaterialServico;
import com.projetos.henrique.projeto2.models.Solicitacao;
import com.projetos.henrique.projeto2.services.MaterialServicoService;
import com.projetos.henrique.projeto2.services.SolicitacaoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/solicitacao")
public class SolicitacaoController {

	final SolicitacaoService solicitacaoService;
	final MaterialServicoService materialServicoService;
	
	public SolicitacaoController(SolicitacaoService solicitacaoService, MaterialServicoService materialServicoService) {
		this.solicitacaoService = solicitacaoService;
		this.materialServicoService = materialServicoService;
	}
	
	@PostMapping
	public ResponseEntity<Object> inserirSolicitacao(@RequestBody @Valid SolicitacaoRequestDto solicitacaoRequest){
		
		Solicitacao solicitacao = new Solicitacao();
		BeanUtils.copyProperties(solicitacaoRequest.getSolicitacaoDto(), solicitacao);
		
		List<MaterialServico> materiaisServicos = new ArrayList<MaterialServico>();
		for(MaterialServicoDto materialServicoDto : solicitacaoRequest.getMateriaisServicosDto()) {
			
			MaterialServico materialServico = new MaterialServico();
			
			BeanUtils.copyProperties(materialServicoDto, materialServico);
			materialServicoService.inserirMaterialServico(materialServico);
			
			materiaisServicos.add(materialServico);
		}
		SolicitacaoResponseDto response = new SolicitacaoResponseDto();
		response.setSolicitacao(solicitacao);
		response.setMateriaisServicos(materiaisServicos);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(materialServicoService);
	}
	
	@GetMapping
	public ResponseEntity<Page<Solicitacao>> getAllByUsuario(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam UUID idUsuario){
		return ResponseEntity.status(HttpStatus.OK).body(solicitacaoService.findAllByUsuario(pageable, idUsuario));
	}
	
	@GetMapping("/{idSolicitacao}")
	public ResponseEntity<Object> getById(@PathVariable(value = "idSolicitacao") UUID idSolicitacao){
		
		SolicitacaoResponseDto solicitacaoResponseDto = new SolicitacaoResponseDto();
		
		Optional<Solicitacao> solicitacaoOptional = solicitacaoService.findById(idSolicitacao);
		if(!solicitacaoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitacao Not Found");
		}
		solicitacaoResponseDto.setSolicitacao(solicitacaoOptional.get());
		
		Optional<List<MaterialServico>> materiaisServicosOptional = materialServicoService.findAllBySolicitacao(idSolicitacao);
		if(!materiaisServicosOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materiais/Serviços Not Found");
		}
		solicitacaoResponseDto.setMateriaisServicos(materiaisServicosOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(solicitacaoResponseDto);
	}
	
	@DeleteMapping("/{idSolicitacao}")
	public ResponseEntity<Object> deletarSolicitacao(@PathVariable(value = "idSolicitacao") UUID idSolicitacao){
		
		Optional<Solicitacao> solicitacaoOptional = solicitacaoService.findById(idSolicitacao);
		
		if(!solicitacaoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitacao Not Found");
		}
		
		Optional<List<MaterialServico>> materiaisServicosOptional = materialServicoService.findAllBySolicitacao(idSolicitacao);
		if(!materiaisServicosOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materiais/Serviços Not Found");
		}
		
		solicitacaoService.deletarSolicitacao(solicitacaoOptional.get());
		
		for(MaterialServico materialServico : materiaisServicosOptional.get()) {
			materialServicoService.deletarMaterialServico(materialServico);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Solicitacao Deleted Successfully");
	}
}
