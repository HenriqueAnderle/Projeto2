package com.projetos.henrique.projeto2.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetos.henrique.projeto2.models.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, UUID>{

	boolean existsByNome(String nome);
	
	boolean existsByEmail(String email);
		
	Page<Contato> findAllByUsuario_IdUsuario(Pageable pageable, UUID idUsuario);
	
	Optional<Contato> findByIdContatoAndUsuario_IdUsuario(UUID idContato, UUID idUsuario);
}
