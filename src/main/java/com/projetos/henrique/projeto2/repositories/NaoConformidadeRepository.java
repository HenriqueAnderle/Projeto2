package com.projetos.henrique.projeto2.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.henrique.projeto2.models.NaoConformidade;

public interface NaoConformidadeRepository extends JpaRepository<NaoConformidade, UUID>{

	Page<NaoConformidade> findAllByRelatorio(Pageable pageable, UUID idRelatorio);
	
}
