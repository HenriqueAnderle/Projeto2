package com.projetos.henrique.projeto2.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.henrique.projeto2.models.MaterialServico;

public interface MaterialServicoRepository extends JpaRepository<MaterialServico, UUID>{

	Page<MaterialServico> findAllBySolicitacao(Pageable pageable, UUID idSolicitacao);
		
}
