package com.projetos.henrique.projeto2.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.models.MaterialServico;
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
	
	//Deletar esse método depois
	public Page<MaterialServico> findAllMaterialServico(Pageable pageable){
		return materialServicoRepository.findAll(pageable);
	}
	
	public Optional<List<MaterialServico>> findAllBySolicitacao(UUID idSolicitacao){
		return materialServicoRepository.findAllBySolicitacao_IdSolicitacao(idSolicitacao);
	}
}
