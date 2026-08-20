package com.projetos.henrique.projeto2.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.henrique.projeto2.models.Contato;

public interface ContatoRepository extends JpaRepository<Contato, UUID>{

	boolean existsByNome(String nome);
	
	boolean existsByEmail(String email);
		
	Page<Contato> findAllByUsuario(Pageable pageable, UUID idUsuario);
	
}
