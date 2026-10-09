package com.projetos.henrique.projeto2.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetos.henrique.projeto2.models.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, UUID>{

	boolean existsByNome(String nome);
	
	Page<Obra> findAllByUsuario_IdUsuario(Pageable pageable, UUID idUsuario);
	
	Optional<Obra> findByIdObraAndUsuario_IdUsuario(UUID idObra, UUID idUsuario);
	
	Optional<Obra> findByNome(String nome);
}
