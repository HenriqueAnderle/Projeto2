package com.projetos.henrique.projeto2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.models.Obra;
import com.projetos.henrique.projeto2.repositories.ObraRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ObraService {

	final ObraRepository obraRepository;
	
	public ObraService(ObraRepository obraRepository) {
		this.obraRepository = obraRepository;
	}
	
	public Obra inserirObra(Obra obra) {
		return obraRepository.save(obra);
	}
	
	public void deletarObra(Obra obra) {
		obraRepository.delete(obra);
	}
	
	public Page<Obra> findAllObras(Pageable pageable){
		return obraRepository.findAll(pageable);
	}
	
	public boolean existsByNome(String nome) {
		return obraRepository.existsByNome(nome);
	}
	
}
