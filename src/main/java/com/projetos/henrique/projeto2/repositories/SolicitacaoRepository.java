package com.projetos.henrique.projeto2.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetos.henrique.projeto2.models.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, UUID>{

	Page<Solicitacao> findAllByUsuario_IdUsuario(Pageable pageable, UUID idUsuario);
}
