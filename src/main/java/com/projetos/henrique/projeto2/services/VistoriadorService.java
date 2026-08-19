package com.projetos.henrique.projeto2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.models.Relatorio;
import com.projetos.henrique.projeto2.models.Vistoriador;
import com.projetos.henrique.projeto2.repositories.VistoriadorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VistoriadorService {

	final VistoriadorRepository vistoriadorRepository;
	
	public VistoriadorService(VistoriadorRepository vistoriadorRepository) {
		this.vistoriadorRepository = vistoriadorRepository;
	}
	
	public Vistoriador inserirVistoriador(Vistoriador vistoriador) {
		return vistoriadorRepository.save(vistoriador);
	}
	
	public void deletarVistoriador(Vistoriador vistoriador) {
		vistoriadorRepository.delete(vistoriador);
	}
	
	public boolean existsByNome(String nome) {
		return vistoriadorRepository.existsByNome(nome);
	}
	
	public Page<Vistoriador> findAllVistoriadores(Pageable pageable){
		return vistoriadorRepository.findAll(pageable);
	}
	
	public Page<Vistoriador> findAllByRelatorio(Pageable pageable, Relatorio relatorio){
		return vistoriadorRepository.findAllByRelatorio(pageable, relatorio);
	}
	
}
