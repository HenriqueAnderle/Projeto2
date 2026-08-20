package com.projetos.henrique.projeto2.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetos.henrique.projeto2.models.Contato;
import com.projetos.henrique.projeto2.repositories.ContatoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContatoService {

	final ContatoRepository contatoRepository;
	
	public ContatoService(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
	}
	
	public Contato inserirContato(Contato contato) {
		return contatoRepository.save(contato);
	}
	
	public void deletarContato(Contato contato) {
		contatoRepository.delete(contato);
	}
	
	public Optional<Contato> findById(UUID idUsuario) {
		return contatoRepository.findById(idUsuario);
	}
	
	//Excluir esse método depois
	public Page<Contato> findAllContatos(Pageable pageable){
		return contatoRepository.findAll(pageable);
	}
	
	public Page<Contato> findAllByUsuario(Pageable pageable, UUID idUsuario){
		return contatoRepository.findAllByUsuario(pageable, idUsuario);
	}
	
	public boolean existsByNome(String nome) {
		return contatoRepository.existsByNome(nome);
	}
	
	public boolean existsByEmail(String email) {
		return contatoRepository.existsByEmail(email);
	}
	
}
