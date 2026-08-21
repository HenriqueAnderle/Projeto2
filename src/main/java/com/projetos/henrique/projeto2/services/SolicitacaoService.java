package com.projetos.henrique.projeto2.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.models.Solicitacao;
import com.projetos.henrique.projeto2.repositories.SolicitacaoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SolicitacaoService {

	final SolicitacaoRepository solicitacaoRepository;
	
	public SolicitacaoService(SolicitacaoRepository solicitacaoRepository) {
		this.solicitacaoRepository = solicitacaoRepository;
	}
	
	public Solicitacao inserirSolicitacao(Solicitacao solicitacao) {
		return solicitacaoRepository.save(solicitacao);
	}
	
	public void deletarSolicitacao(Solicitacao solicitacao) {
		solicitacaoRepository.delete(solicitacao);
	}
	
	public Optional<Solicitacao> findById(UUID idSolicitacao){
		return solicitacaoRepository.findById(idSolicitacao);
	}
	
	public Page<Solicitacao> findAllByUsuario(Pageable pageable, UUID idUsuario){
		return solicitacaoRepository.findAllByUsuario_IdUsuario(pageable, idUsuario);
	}
	
}
