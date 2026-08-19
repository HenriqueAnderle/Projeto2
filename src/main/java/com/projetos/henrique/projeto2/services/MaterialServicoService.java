package com.projetos.henrique.projeto2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.models.MaterialServico;
import com.projetos.henrique.projeto2.models.Solicitacao;
import com.projetos.henrique.projeto2.repositories.MaterialServicoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaterialServicoService {

	final MaterialServicoRepository materialServicoRepository;
	
	public MaterialServicoService(MaterialServicoRepository meterialServicoRepository) {
		this.materialServicoRepository = meterialServicoRepository;
	}
	
	public MaterialServico inserirMaterialServico(MaterialServico materialServico) {
		return materialServicoRepository.save(materialServico);
	}
	
	public void deletarMaterialServico(MaterialServico materialServico) {
		materialServicoRepository.delete(materialServico);
	}
	
	public Page<MaterialServico> findAllMaterialServico(Pageable pageable){
		return materialServicoRepository.findAll(pageable);
	}
	
	public Page<MaterialServico> findAllBySolicitacao(Pageable pageable, Solicitacao solicitacao){
		return materialServicoRepository.findAllBySolicitacao(pageable, solicitacao);
	}
}
