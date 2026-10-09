package com.projetos.henrique.projeto2.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetos.henrique.projeto2.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

	boolean existsByEmail(String email);
	
	Optional<Usuario> findByEmail(String email);

}
