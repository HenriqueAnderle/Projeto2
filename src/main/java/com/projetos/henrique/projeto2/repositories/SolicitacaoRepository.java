package com.projetos.henrique.projeto2.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.henrique.projeto2.models.Solicitacao;
import com.projetos.henrique.projeto2.models.Usuario;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, UUID>{

	Page<Solicitacao> findAllByUsuario(Pageable pageable, Usuario usuario);
}
