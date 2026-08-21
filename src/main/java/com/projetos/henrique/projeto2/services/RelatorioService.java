package com.projetos.henrique.projeto2.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.models.Relatorio;
import com.projetos.henrique.projeto2.repositories.RelatorioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RelatorioService {

	final RelatorioRepository relatorioRepository;
	
	public RelatorioService(RelatorioRepository relatorioRepository) {
		this.relatorioRepository = relatorioRepository;
	}
	
	public Relatorio inserirRelatorio(Relatorio relatorio) {
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
