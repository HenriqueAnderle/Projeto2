package com.projetos.henrique.projeto2.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.henrique.projeto2.models.NaoConformidade;

public interface NaoConformidadeRepository extends JpaRepository<NaoConformidade, UUID>{

	Optional<List<NaoConformidade>> findAllByRelatorio_IdRelatorio(UUID idRelatorio);
		
}
