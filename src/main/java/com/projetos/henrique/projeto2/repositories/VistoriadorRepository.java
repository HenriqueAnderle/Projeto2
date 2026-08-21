package com.projetos.henrique.projeto2.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.henrique.projeto2.models.Vistoriador;

public interface VistoriadorRepository extends JpaRepository<Vistoriador, UUID>{

	boolean existsByNome(String nome);
	
	Page<Vistoriador> findAllByRelatorio_IdRelatorio(Pageable pageable, UUID idRelatorio);
	
}
