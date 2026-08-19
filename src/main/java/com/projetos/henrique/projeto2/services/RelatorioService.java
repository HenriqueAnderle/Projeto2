package com.projetos.henrique.projeto2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.models.Relatorio;
import com.projetos.henrique.projeto2.models.Usuario;
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
	
	public Page<Relatorio> findAllRelatorio(Pageable pageable){
		return relatorioRepository.findAll(pageable);
	}
	
	public Page<Relatorio> findAllByUsuario(Pageable pageable, Usuario usuario){
		return relatorioRepository.findAllByUsuario(pageable, usuario);
	}
}
