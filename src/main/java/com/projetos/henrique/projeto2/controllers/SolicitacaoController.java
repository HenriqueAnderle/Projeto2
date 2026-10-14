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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.henrique.projeto2.dtos.MaterialServicoDto;
import com.projetos.henrique.projeto2.dtos.SolicitacaoDto;
import com.projetos.henrique.projeto2.dtos.SolicitacaoRequestDto;
import com.projetos.henrique.projeto2.models.MaterialServico;
import com.projetos.henrique.projeto2.models.Solicitacao;
import com.projetos.henrique.projeto2.models.Usuario;
import com.projetos.henrique.projeto2.services.MaterialServicoService;
import com.projetos.henrique.projeto2.services.SolicitacaoService;
import com.projetos.henrique.projeto2.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/solicitacao")
public class SolicitacaoController {

	final SolicitacaoService solicitacaoService;
	final MaterialServicoService materialServicoService;
	final UsuarioService usuarioService;
	
	public SolicitacaoController(SolicitacaoService solicitacaoService, MaterialServicoService materialServicoService, UsuarioService usuarioService) {
		this.solicitacaoService = solicitacaoService;
		this.materialServicoService = materialServicoService;
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Object> inserirSolicitacao(@RequestBody @Valid SolicitacaoRequestDto solicitacaoRequest, Authentication authentication){
		
		Solicitacao solicitacao = new Solicitacao();
		BeanUtils.copyProperties(solicitacaoRequest.getSolicitacaoDto(), solicitacao);
		
		solicitacao.setUsuario(usuarioService.findByEmail(authentication.getName()).get());
		
		List<MaterialServico> materiaisServicos = new ArrayList<MaterialServico>();
		for(MaterialServicoDto materialServicoDto : solicitacaoRequest.getMateriaisServicosDto()) {
			
			MaterialServico materialServico = new MaterialServico();
			
			materialServico.setSolicitacao(solicitacao);
			
			BeanUtils.copyProperties(materialServicoDto, materialServico);
			materialServicoService.inserirMaterialServico(materialServico);
			
			materiaisServicos.add(materialServico);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoRequest);
	}
	
	@GetMapping
	public ResponseEntity<Page<Solicitacao>> getAllByUsuario(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable,
			Authentication authentication){
		Optional<Usuario> usuario = usuarioService.findByEmail(authentication.getName());
		
		return ResponseEntity.status(HttpStatus.OK).body(solicitacaoService.findAllByUsuario(pageable, usuario.get().getIdUsuario()));
	}
	
	@GetMapping("/{idSolicitacao}")
	public ResponseEntity<Object> getById(@PathVariable(value = "idSolicitacao") UUID idSolicitacao, Authentication authentication){
		
		SolicitacaoRequestDto solicitacaoRequestDto = new SolicitacaoRequestDto();
		
		Optional<Usuario> usuario = usuarioService.findByEmail(authentication.getName());
		
		Optional<Solicitacao> solicitacaoOptional = solicitacaoService.findAllByIdSolcitiacaoAndUsuario_IdUsuario(idSolicitacao, usuario.get().getIdUsuario());
		if(!solicitacaoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitacao Not Found");
		}
		solicitacaoRequestDto.setSolicitacaoDto(SolicitacaoDto.fromEntity(solicitacaoOptional.get()));
		
		Optional<List<MaterialServico>> materiaisServicosOptional = materialServicoService.findAllBySolicitacao(idSolicitacao);
		if(!materiaisServicosOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materiais/Serviços Not Found");
		}
		solicitacaoRequestDto.setMateriaisServicosDto(MaterialServicoDto.fromEntity(materiaisServicosOptional.get()));
		
		return ResponseEntity.status(HttpStatus.OK).body(solicitacaoRequestDto);
	}
	
	@DeleteMapping("/{idSolicitacao}")
	public ResponseEntity<Object> deletarSolicitacao(@PathVariable(value = "idSolicitacao") UUID idSolicitacao, Authentication authentication){
		
		Optional<Usuario> usuario = usuarioService.findByEmail(authentication.getName());
		
		Optional<Solicitacao> solicitacaoOptional = solicitacaoService.findAllByIdSolcitiacaoAndUsuario_IdUsuario(idSolicitacao, usuario.get().getIdUsuario());
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
		
		return ResponseEntity.status(HttpStatus.OK).body("Solicitacao And Materiais/Serviços Deleted Successfully");
	}
}
