package com.projetos.henrique.projeto2.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.henrique.projeto2.models.Vistoriador;

public interface VistoriadorRepository extends JpaRepository<Vistoriador, UUID>{

	boolean existsByNome(String nome);
	
	Optional<List<Vistoriador>> findAllByRelatorio_IdRelatorio(UUID idRelatorio);
	
}
