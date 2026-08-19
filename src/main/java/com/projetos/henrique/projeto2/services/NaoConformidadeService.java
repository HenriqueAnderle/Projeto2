package com.projetos.henrique.projeto2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.models.NaoConformidade;
import com.projetos.henrique.projeto2.models.Relatorio;
import com.projetos.henrique.projeto2.repositories.NaoConformidadeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NaoConformidadeService {

	final NaoConformidadeRepository naoConformidadeRepository;
	
	public NaoConformidadeService(NaoConformidadeRepository naoConformidadeRepository) {
		this.naoConformidadeRepository = naoConformidadeRepository;
	}
	
	public NaoConformidade inserirNaoConformidade(NaoConformidade naoConformidade) {
		return naoConformidadeRepository.save(naoConformidade);
	}
	
	public void deletarNaoConformidade(NaoConformidade naoConformidade) {
		naoConformidadeRepository.delete(naoConformidade);
	}
	
	public Page<NaoConformidade> findAllNaoConformidade(Pageable pageable){
		return naoConformidadeRepository.findAll(pageable);
	}
	
	public Page<NaoConformidade> findAllByRelatorio(Pageable pageable, Relatorio relatorio){
		return naoConformidadeRepository.findAllByRelatorio(relatorio, pageable);
	}
}
