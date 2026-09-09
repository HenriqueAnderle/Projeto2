package com.projetos.henrique.projeto2.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.dtos.RelatorioDto;
import com.projetos.henrique.projeto2.models.Obra;
import com.projetos.henrique.projeto2.models.Relatorio;
import com.projetos.henrique.projeto2.models.Usuario;
import com.projetos.henrique.projeto2.repositories.ObraRepository;
import com.projetos.henrique.projeto2.repositories.RelatorioRepository;
import com.projetos.henrique.projeto2.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RelatorioService {

	final RelatorioRepository relatorioRepository;
	final ObraRepository obraRepository;
	final UsuarioRepository usuarioRepository;
	
	public RelatorioService(RelatorioRepository relatorioRepository, ObraRepository obraRepository, UsuarioRepository usuarioRepository) {
		this.relatorioRepository = relatorioRepository;
		this.obraRepository = obraRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	public Relatorio inserirRelatorio(RelatorioDto relatorioDto) {
		
		Relatorio relatorio = new Relatorio();
		BeanUtils.copyProperties(relatorioDto, relatorio);
		
		Obra obra = obraRepository.findByNome(relatorioDto.getNomeObra())
				.orElseThrow(() -> new RuntimeException("Nome Obra Not Found"));
		
		Usuario usuario = usuarioRepository.findById(relatorioDto.getIdUsuario())
				.orElseThrow(() -> new RuntimeException("Id Usuario Not Found"));
		
		relatorio.setObra(obra);
		relatorio.setUsuario(usuario);
		
		return relatorioRepository.save(relatorio);
	}
	
	public void deletarRelatorio(Relatorio relatorio) {
		relatorioRepository.delete(relatorio);
	}
	
	public Optional<Relatorio> findById(UUID idRelatorio){
		return relatorioRepository.findById(idRelatorio);
	}
	
	public Page<Relatorio> findAllByUsuario(Pageable pageable, UUID idUsuario){
		return relatorioRepository.findAllByUsuario_IdUsuario(pageable, idUsuario);
	}
}
