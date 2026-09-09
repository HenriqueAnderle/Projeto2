package com.projetos.henrique.projeto2.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.henrique.projeto2.models.MaterialServico;

public interface MaterialServicoRepository extends JpaRepository<MaterialServico, UUID>{

	Optional<List<MaterialServico>> findAllBySolicitacao_IdSolicitacao(UUID idSolicitacao);
		
}
