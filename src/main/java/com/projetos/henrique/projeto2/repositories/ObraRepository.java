package com.projetos.henrique.projeto2.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.henrique.projeto2.models.Obra;

public interface ObraRepository extends JpaRepository<Obra, UUID>{

	boolean existsByNome(String nome);
	
	Page<Obra> findAllByUsuario_IdUsuario(Pageable pageable, UUID idUsuario);
}
